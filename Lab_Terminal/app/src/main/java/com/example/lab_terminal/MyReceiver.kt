package com.example.lab_terminal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    var state: String? = null
    override fun onReceive(context: Context, intent: Intent) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        // checking whether airplane mode is enabled or not
        if (isAirplaneModeEnabled) {
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
        }
    }
}