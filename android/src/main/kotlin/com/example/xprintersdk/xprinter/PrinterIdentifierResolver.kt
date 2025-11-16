package com.example.xprintersdk.xprinter

import android.util.Log
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting

object PrinterIdentifierResolver {
    fun resolve(businessSetting: BusinessSetting?): String? {
        if (businessSetting == null) return null
        val connection = businessSetting.printerConnection?.lowercase() ?: return null
        Log.e("printerkey", "resolve: ${connection}", )
        return when (connection) {
            "ipconnection" -> businessSetting.ip?.takeIf { it.isNotBlank() }
            "usbconnection" -> businessSetting.xprinterpath?.takeIf { it.isNotBlank() }
            else -> null
        }
    }
}
