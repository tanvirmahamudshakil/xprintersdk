package com.example.xprintersdk.NyxPrinter

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent

import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.IBinder
import android.os.RemoteException
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import com.example.xprintersdk.NyxPrinter.Result.msg
import com.example.xprintersdk.NyxPrinter.print.PrintTextFormat
import com.example.xprintersdk.NyxPrinter.print.IPrinterService
import com.example.xprintersdk.R
import io.flutter.plugin.common.MethodChannel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.Executors
import timber.log.Timber


class NyxPrinterActivity(var context: Context) {
    private val TAG = "MainActivity"
     var btnVer: Button? = null
     var btnPaper: Button? = null
     var btn1: Button? = null
     var btn2: Button? = null
     var btn3: Button? = null
     var btnScan: Button? = null
     var tvLog: TextView? = null

     val RC_SCAN = 0x99
    var PRN_TEXT: String? = null
     var btn4: Button? = null
     var btnLbl: Button? = null
     var btnLblLearning: Button? = null
     val singleThreadExecutor = Executors.newSingleThreadExecutor()
     val handler = Handler()
    var version = arrayOfNulls<String>(1)
     var printerService: IPrinterService? = null


    private val connService: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            showLog("printer service disconnected, try reconnect")
            printerService = null
            // 尝试重新bind
            handler.postDelayed({ bindServiceData() }, 5000)
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("onServiceConnected: %s", name)
            printerService = IPrinterService.Stub.asInterface(service)
            getVersion()
        }
    }


     fun bindServiceData() {
        val intent = Intent()
        intent.setPackage("com.example.xprintersdk.NyxPrinter")
        intent.setAction("com.example.xprintersdk.NyxPrinter.IPrinterService")
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE)
    }

     fun unbindServiceData() {
        context.unbindService(connService)
    }

//    @SingleClick
//    fun onClick(view: View) {
//        if (view.id == R.id.btn_ver) {
//            getVersion()
//        } else if (view.id == R.id.btn_paper) {
//            paperOut()
//        } else if (view.id == R.id.btn1) {
//            printText()
//        } else if (view.id == R.id.btn2) {
//            printBarcode()
//        } else if (view.id == R.id.btn3) {
//            printQrCode()
//        } else if (view.id == R.id.btn4) {
//            printBitmap()
//        } else if (view.id == R.id.btn_scan) {
//            scan()
//        } else if (view.id == R.id.btn_lbl) {
//            printLabel()
//        } else if (view.id == R.id.btn_lbl_learning) {
//            printLabelLearning()
//        }
//    }

    private val qscReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("com.android.NYX_QSC_DATA" == intent.action) {
                val qsc = intent.getStringExtra("qsc")
                showLog("qsc scan result: %s", qsc)
                printText("qsc-quick-scan-code\n$qsc")
            }
        }
    }

    private fun registerQscScanReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.android.NYX_QSC_DATA")
       context.registerReceiver(qscReceiver, filter)
    }

    private fun unregisterQscReceiver() {
        context.unregisterReceiver(qscReceiver)
    }

    private fun getVersion() {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.getPrinterVersion(version)
                showLog("Version: " + msg(ret) + "  " + version[0])
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun paperOut() {
        singleThreadExecutor.submit {
            try {
                printerService!!.paperOut(80)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun printText() {
        printText(PRN_TEXT!!)
    }

    private fun printText(text: String) {
        singleThreadExecutor.submit {
            try {

                val textFormat = PrintTextFormat()
                 textFormat.setTextSize(32);
                 textFormat.setUnderline(true);
                val ret = printerService!!.printText(text, textFormat)
                showLog("Print text: " + msg(ret))
                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun printBarcode() {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printBarcode("123456789", 300, 160, 1, 1)
                showLog("Print text: " + msg(ret))
                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun printQrCode() {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printQrCode("123456789", 300, 300, 1)
                showLog("Print barcode: " + msg(ret))
                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

     fun printBitmap(bitmap : Bitmap,result: MethodChannel.Result) {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printBitmap(
                    bitmap,
                    0,
                    1
                )
                showLog("Print bitmap: " + msg(ret))
                if (ret == 0) {
                    paperOut()
                }
                result.success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("NyxPrinter", "printBitmap: ${e}", )
                result.success(false)
            }
        }
    }

    private fun printLabel() {
        singleThreadExecutor.submit {
            try {
                var ret = printerService!!.labelLocate(240, 16)
                if (ret == 0) {
                    val format = PrintTextFormat()
                    printerService!!.printText("\nModel:\t\tNB55", format)
                    printerService!!.printBarcode("1234567890987654321", 320, 90, 2, 0)
                    val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                    printerService!!.printText("Time:\t\t$date", format)
                    ret = printerService!!.labelPrintEnd()
                }
                showLog("Print label: " + msg(ret))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun printLabelLearning() {
//        if (version[0] != null && version[0]!!.toFloat() < 1.10) {
//            showLog(getString(R.string.res_not_support))
//            return
//        }
        singleThreadExecutor.submit {
            var ret = 0
            try {
                if (!printerService!!.hasLabelLearning()) {
                    // label learning
                    ret = printerService!!.labelDetectAuto()
                }
                if (ret == 0) {
                    ret = printerService!!.labelLocateAuto()
                    if (ret == 0) {
                        val format = PrintTextFormat()
                        printerService!!.printText("\nModel:\t\tNB55", format)
                        printerService!!.printBarcode("1234567890987654321", 320, 90, 2, 0)
                        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                        printerService!!.printText("Time:\t\t$date", format)
                        printerService!!.labelPrintEnd()
                    }
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            showLog("Label learning print: " + msg(ret))
        }
    }

    private fun scan() {
        if (!existApp("net.nyx.scanner")) {
            showLog("未安装scanner app")
            return
        }
        val intent = Intent()
        intent.setComponent(
            ComponentName(
                "net.nyx.scanner",
                "net.nyx.scanner.ScannerActivity"
            )
        )
        // set the capture activity actionbar title
        intent.putExtra("TITLE", "Scan")
        // show album icon, default true
        // intent.putExtra("SHOW_ALBUM", true);
        // play beep sound when get the scan result, default true
        // intent.putExtra("PLAY_SOUND", true);
        // play vibrate when get the scan result, default true
        // intent.putExtra("PLAY_VIBRATE", true);
       // startActivityForResult(intent, MainActivity.RC_SCAN)
    }

    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SCAN && resultCode == Activity.RESULT_OK && data != null) {
            val result = data.getStringExtra("SCAN_RESULT")
            showLog("Scanner result: $result")
        }
    }

    fun existApp(pkg: String?): Boolean {
        try {
            return context.getPackageManager().getPackageInfo(pkg!!, 0) != null
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return false
    }

    protected fun onDestroy() {
       // super.onDestroy()
        unbindServiceData()
        unregisterQscReceiver()
    }



//    fun onOptionsItemSelected(item: MenuItem): Boolean {
////        if (item.itemId == R.id.menu_item_clear) {
////            clearLog()
////        }
//       // return super.onOptionsItemSelected(item)
//    }

    private fun initView() {
//        btnVer = findViewById<View>(R.id.btn_ver) as Button
//        btnVer!!.setOnClickListener(this@MainActivity)
//        btnPaper = findViewById<View>(R.id.btn_paper) as Button
//        btnPaper!!.setOnClickListener(this@MainActivity)
//        btn1 = findViewById<View>(R.id.btn1) as Button
//        btn1!!.setOnClickListener(this@MainActivity)
//        btn2 = findViewById<View>(R.id.btn2) as Button
//        btn2!!.setOnClickListener(this@MainActivity)
//        btn3 = findViewById<View>(R.id.btn3) as Button
//        btn3!!.setOnClickListener(this@MainActivity)
//        btnScan = findViewById<View>(R.id.btn_scan) as Button
//        btnScan!!.setOnClickListener(this@MainActivity)
//        tvLog = findViewById<View>(R.id.tv_log) as TextView
//        btn4 = findViewById<View>(R.id.btn4) as Button
//        btn4!!.setOnClickListener(this@MainActivity)
//        btnLbl = findViewById<View>(R.id.btn_lbl) as Button
//        btnLbl!!.setOnClickListener(this@MainActivity)
//        btnLblLearning = findViewById<View>(R.id.btn_lbl_learning) as Button
//        btnLblLearning!!.setOnClickListener(this@MainActivity)
    }

    fun showLog(log: String, vararg args: Any?) {
        var log = log
        if (args != null && args.size > 0) {
            log = String.format(log, *args)
        }
        val res = log
        Log.e(TAG, res)
        val dateFormat: DateFormat = SimpleDateFormat("MM-dd HH:mm:ss")
//        runOnUiThread(Runnable {
//            if (tvLog!!.lineCount > 100) {
//                tvLog!!.text = ""
//            }
//            tvLog!!.append(dateFormat.format(Date()) + ":" + res + "\n")
//            tvLog!!.post { (tvLog!!.parent as ScrollView).fullScroll(View.FOCUS_DOWN) }
//        })
    }

    fun clearLog() {
        tvLog!!.text = ""
    }

}