package com.example.week7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Broadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var switchActivity: Intent = Intent(context, IntentActivity::class.java)
        switchActivity.putExtra("broadcastMessage", "This is a custom broadcast event.")
        context.startActivity(switchActivity)
    }
}