package com.example.homelauncher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PackageAddDeleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val duration = Toast.LENGTH_LONG
        var action:String ="NO Action"
        intent?.let {
            action= it.action.toString()
        }

        Toast.makeText(context, "We got action is $action", duration).show()
    }
}