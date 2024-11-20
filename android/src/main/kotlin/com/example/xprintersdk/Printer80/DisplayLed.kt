package com.example.xprintersdk.Printer80

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface



class DisplayLed(context: Context) {
    init {
//        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
//
//        val port = UsbSerialDevice.createUsbSerialDevice()
//        if (port != null) {
//            port.open()
//            port.setBaudRate(2400)
//            port.setDataBits(UsbSerialInterface.DATA_BITS_8)
//            port.setStopBits(UsbSerialInterface.STOP_BITS_1)
//            port.setParity(UsbSerialInterface.PARITY_NONE)
//
//            val display = GuestDisplay(port)
//            display.sendDisplayInstruction(1, "12345678") // Example: light type 1 and some value
//        }
    }

//    fun connectDisplay() {

//        getUsbDeviceByPath("")

    fun getUsbDeviceByPath(context: Context, devicePath: String): UsbDevice? {
        // Get the UsbManager instance
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager

        // Iterate through the device list to find the matching device by path
        val deviceList = usbManager.deviceList
        for ((key, device) in deviceList) {
            if (key == devicePath) {
                return device // Return the matching UsbDevice
            }
        }

        // If no device matches the path, return null
        return null
    }


}


class GuestDisplay(port: UsbSerialDevice?) {
    private val serialPort: UsbSerialDevice? = port

    fun sendDisplayInstruction(lightType: Int, firstRow: String) {
        if (serialPort == null) return

        var lightCommand = ""
        when (lightType) {
            1 -> lightCommand = "\u001B\u0073\u0032" // ESC s 2
            2 -> lightCommand = "\u001B\u0073\u0033" // ESC s 3
            3 -> lightCommand = "\u001B\u0073\u0034" // ESC s 4
        }
        val message = lightCommand + "\u001B\u0041" + firstRow.trim { it <= ' ' } + "\r"
        serialPort.write(message.toByteArray())
    }
}
