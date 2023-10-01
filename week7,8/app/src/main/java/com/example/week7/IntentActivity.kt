package com.example.week7

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


class IntentActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val myBroadcastReceiver = Broadcast()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val intentFilter = IntentFilter("CUSTOM_EVENT")
        registerReceiver(myBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
        val intent: Intent = this.intent
        val message = intent.getStringExtra("messageContent")
        val broadcast = intent.getStringExtra("broadcastMessage")
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "message send to this activity: $message",
                    fontSize = 50.sp
                )
                Text(
                    text = "message receive from boardcast: $broadcast",
                    fontSize = 50.sp
                )
                Button(onClick = { onBackPressed() }) {
                    Text(text = "back to previous activity")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
        Log.d("Stop Broadcast", "BroadCast Stop ")
    }

}





