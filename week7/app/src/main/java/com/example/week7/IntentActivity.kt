package com.example.week7

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

class IntentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent: Intent = this.intent
        val message = intent.getStringExtra("messageContent")
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
                Button(onClick = { onBackPressed() }) {
                    Text(text = "back to previous activity")
                }
            }
        }
    }
}





