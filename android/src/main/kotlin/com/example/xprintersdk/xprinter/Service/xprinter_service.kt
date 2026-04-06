package com.example.xprintersdk.xprinter.Service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Binder
import android.os.IBinder
import android.util.Log
import net.posprinter.asynncTask.PosAsynncTask
import net.posprinter.posprinterface.BackgroundInit
import net.posprinter.posprinterface.PrinterBinder
import net.posprinter.posprinterface.ProcessData
import net.posprinter.posprinterface.TaskCallback
import net.posprinter.utils.PosPrinterDev
import net.posprinter.utils.RoundQueue

class XprinterConnectedService : Service() {
    private val TAG = "PrinterConnectionService"
    private val printers = HashMap<String, Printer>()
    private val PORT = 9100
    private val myBinder: IBinder = XPrinterBinder()

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    override fun onCreate() {
        super.onCreate()
    }

    @SuppressLint("LongLogTag")
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        for (printer in printers.values) {
            if (printer.xPrinterDev != null) {
                printer.xPrinterDev?.Close()
            }
        }

        printers.clear()
    }

    fun getPrinter(ip: String): Printer? {
        return printers[ip]
    }

    @SuppressLint("LongLogTag")
    fun removePrinter(ip: String) {
        Log.d(TAG, "removePrinter")
        printers.remove(ip)
    }

    inner class XPrinterBinder : Binder(), PrinterBinder {

        override fun connectBtPort(bluetoothID: String, callback: TaskCallback) {
            val oldPrinter = getPrinter(bluetoothID)
            if (oldPrinter != null) {
                removePrinter(bluetoothID)
            }

            val task = PosAsynncTask(callback, object : BackgroundInit {
                override fun doinbackground(): Boolean {
                    val printer = Printer()
                    printer.que = printer.getInstanceRoundQueue()
                    printer.xPrinterDev = PosPrinterDev(PosPrinterDev.PortType.Bluetooth, bluetoothID)
                    printer.BtPathName = bluetoothID
                    printer.mMsg = printer.xPrinterDev?.Open()
                    printer.type = PosPrinterDev.PortType.Bluetooth.name

                    val flag = if (printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.OpenPortSuccess) {
                        printer.isConnected = true
                        true
                    } else {
                        false
                    }

                    if (flag) {
                        printers[bluetoothID] = printer
                    }
                    return flag
                }
            })
            task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR)
        }

        override fun connectUsbPort(context: Context, usbPathName: String, callback: TaskCallback) {
            val oldPrinter = getPrinter(usbPathName)
            if (oldPrinter != null) {
                oldPrinter.xPrinterDev?.Close()
                removePrinter(usbPathName)
            }

            val task = PosAsynncTask(callback, object : BackgroundInit {
                @SuppressLint("LogNotTimber", "LongLogTag")
                override fun doinbackground(): Boolean {
                    val printer = Printer()
                    printer.ip = null
                    printer.usbPathName = usbPathName
                    printer.que = printer.getInstanceRoundQueue()
                    printer.xPrinterDev = PosPrinterDev(PosPrinterDev.PortType.USB, context, usbPathName)
                    printer.mMsg = printer.xPrinterDev?.Open()
                    printer.type = PosPrinterDev.PortType.USB.name

                    val flag = if (printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.OpenPortSuccess) {
                        printer.isConnected = true
                        true
                    } else {
                        printer.isConnected = false
                        false
                    }

                    Log.d(TAG, "connectUsbPort flag:$flag")
                    printers[usbPathName] = printer
                    return flag
                }
            })
            task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR)
        }

        override fun connectNetPort(ip: String, callback: TaskCallback) {
            val oldPrinter = getPrinter(ip)
            if (oldPrinter != null) {
//                removePrinter(ip)
                oldPrinter.xPrinterDev?.Close()
                removePrinter(ip)
            }

            val task = PosAsynncTask(callback, object : BackgroundInit {
                override fun doinbackground(): Boolean {
                    val printer = Printer()
                    printer.ip = ip
                    printer.que = printer.getInstanceRoundQueue()
                    printer.xPrinterDev = PosPrinterDev(PosPrinterDev.PortType.Ethernet, ip, PORT)
                    printer.mMsg = printer.xPrinterDev?.Open()
                    printer.type = PosPrinterDev.PortType.Ethernet.name

                    var flag = false
                    try {
                        flag = if (printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.OpenPortSuccess) {
                            printer.isConnected = true
                            true
                        } else {
                            false
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        flag = false
                    }

                    if (flag) {
                        printers[ip] = printer
                    }

                    return flag
                }
            })
            task.execute()
        }

        @SuppressLint("LongLogTag")
        override fun disconnectCurrentPort(ip: String, callback: TaskCallback) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "disconnectCurrentPort ip: $ip, 打印机未添加")
                callback.OnFailed()
            } else {
                val task = PosAsynncTask(callback, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        printer.mMsg = printer.xPrinterDev?.Close()
                        val flag = if (printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.ClosePortSuccess) {
                            printer.isConnected = false
                            printer.que?.clear()
                            true
                        } else {
                            false
                        }
                        return flag
                    }
                })
                task.execute()
            }
        }

        @SuppressLint("LongLogTag")
        override fun disconnectAll(callback: TaskCallback) {
            val count = printers.size
            if (count == 0) {
                Log.d(TAG, "disconnectAll count: $count")
                callback.OnSucceed()
            } else {
                val task = PosAsynncTask(callback, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        for (printer in printers.values) {
                            val ip = printer.ip
                            if (ip != null) {
                                this@XPrinterBinder.disconnectCurrentPort(ip, object : TaskCallback {
                                    override fun OnSucceed() {}
                                    override fun OnFailed() {}
                                })
                            }
                        }
                        return true
                    }
                })
                task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR)
            }
        }

        @SuppressLint("LongLogTag")
        override fun acceptdatafromprinter(ip: String, callback: TaskCallback) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "acceptdatafromprinter ip: $ip, 打印机未添加")
                callback.OnFailed()
            } else {
                PosAsynncTask(callback, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        printer.que = printer.getInstanceRoundQueue()
                        val buffer = ByteArray(4)
                        printer.que?.clear()
                        Log.i("TAG", printer.xPrinterDev?.Read(buffer)?.GetErrorCode().toString())
                        printer.que?.addLast(buffer)
                        Log.i("TAG", "开始读取${printer.que?.last?.contentToString()}")

                        while (printer.xPrinterDev?.Read(buffer)?.GetErrorCode() == PosPrinterDev.ErrorCode.ReadDataSuccess) {
                            try {
                                Thread.sleep(500L)
                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                                return false
                            }
                        }

                        printer.isConnected = false
                        return false
                    }
                })
            }
        }

        @SuppressLint("LongLogTag")
        override fun readBuffer(ip: String): RoundQueue<ByteArray>? {
            val printer = getPrinter(ip)
            return if (printer == null) {
                Log.d(TAG, "readBuffer ip: $ip, 打印机未添加")
                null
            } else {
                RoundQueue<ByteArray>(500) // original code just created one and ignored
                printer.que
            }
        }

        @SuppressLint("LongLogTag")
        override fun clearBuffer(ip: String) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "clearBuffer ip: $ip, 打印机未添加")
            } else {
                printer.que?.clear()
            }
        }

        @SuppressLint("LongLogTag")
        override fun checkLinkedState(ip: String, execute: TaskCallback) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "checkLinkedState ip: $ip, 打印机未添加")
                execute.OnFailed()
            } else {
                val task = PosAsynncTask(execute, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        return if (printer.isConnected) {
                            val msg = printer.xPrinterDev?.Open()
                            printer.isConnected = msg?.GetErrorCode() == PosPrinterDev.ErrorCode.OpenPortSuccess
                            printer.isConnected
                        } else {
                            false
                        }
                    }
                })
                task.execute()
            }
        }

        @SuppressLint("LongLogTag")
        override fun write(ip: String, data: ByteArray?, callback: TaskCallback) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "write ip: $ip, 打印机未添加")
                callback.OnFailed()
            } else {
                val task = PosAsynncTask(callback, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        if (data != null) {
                            try {
                                printer.mMsg = printer.xPrinterDev?.Write(data)
                                if (printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.WriteDataSuccess) {
                                    printer.isConnected = true
                                    return true
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            printer.isConnected = false
                        }
                        return false
                    }
                })
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }
        }

        @SuppressLint("LongLogTag")
        override fun writeDataByYouself(
            ip: String,
            callback: TaskCallback,
            processData: ProcessData
        ) {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "writeDataByYouself ip: $ip, 打印机未添加")
                callback.OnFailed()
            } else {
                val task = PosAsynncTask(callback, object : BackgroundInit {
                    override fun doinbackground(): Boolean {
                        val list = processData.processDataBeforeSend() ?: return false

                        for (bytes in list) {
                            printer.mMsg = printer.xPrinterDev?.Write(bytes)
                        }

                        return printer.mMsg?.GetErrorCode() == PosPrinterDev.ErrorCode.WriteDataSuccess
                    }
                })
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }
        }

        @SuppressLint("LongLogTag")
        override fun isConnect(ip: String): Boolean {
            val printer = getPrinter(ip)
            if (printer == null) {
                Log.d(TAG, "isConnect ip: $ip, 打印机未添加")
                return false
            } else {
                try {
                    printer.isConnected = printer.xPrinterDev?.GetPortInfo()?.PortIsOpen() == true
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                return printer.isConnected
            }
        }
    }

    inner class Printer {
        var xPrinterDev: PosPrinterDev? = null
        var mMsg: PosPrinterDev.ReturnMessage? = null
        var isConnected: Boolean = false
        var que: RoundQueue<ByteArray>? = null
        var ip: String? = null
        var type: String? = null
        var usbPathName: String? = null
        var BtPathName: String? = null

        fun getInstanceRoundQueue(): RoundQueue<ByteArray> {
            if (que == null) {
                que = RoundQueue(500)
            }
            return que!!
        }
    }


}