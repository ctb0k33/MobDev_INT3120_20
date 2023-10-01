package com.example.week7

import android.app.SearchManager
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.week7.ui.theme.Week7Theme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myBroadcastReceiver = Broadcast()
        val intentFilter = IntentFilter("CUSTOM_EVENT")
        registerReceiver(myBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
        setContent {
            Week7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val (message, setMessage) = remember {
                        mutableStateOf("")
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column {
                            Text(text = "Message: ")
                            TextField(value = message, onValueChange = {
                                setMessage(it)
                            })
                        }

                        Button(onClick = { sendMessageToOtherActivity(message) }) {
                            Text(text = "Send message to other activity")
                        }
                        Button(onClick = { callAnAmbulance() }) {
                            Text(text = "Call an ambulance")
                        }
                        Button(onClick = { searchInternet() }) {
                            Text(text = "search youtube.com")
                        }
                        Button(onClick = { sendMessageViaSMS() }) {
                            Text(text = "send welcome to the international via SMS")
                        }
                        Button(onClick = { showImage() }) {
                            Text(text = "get image")
                        }
                        Button(onClick = { showContact() }) {
                            Text(text = "show contact")
                        }
                        Button(onClick = { show8Contact() }) {
                            Text(text = "show 8 contact")
                        }
                        Button(onClick = { showMap() }) {
                            Text(text = "show map")
                        }
                        Button(onClick = { showMusic() }) {
                            Text(text = "show music")
                        }
                        Button(onClick = { boardCastIntent() }) {
                            Text(text = "board cast intent")
                        }
                    }
                }
            }
        }
    }

    fun sendMessageToOtherActivity(message: String) {
        var sendMessageToOtherAc: Intent = Intent(this, IntentActivity::class.java)
        sendMessageToOtherAc.putExtra("messageContent", message)
        this.startActivity(sendMessageToOtherAc)
    }

    fun callAnAmbulance() {
        var callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0963124504"))
        startActivity(callIntent)
    }

    fun searchInternet() {
        var searchIntent: Intent = Intent(Intent.ACTION_WEB_SEARCH)
        searchIntent.putExtra(SearchManager.QUERY, "youtube.com")
        startActivity(searchIntent)
    }

    fun sendMessageViaSMS() {
        var messageSMS: Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:09123123"))
        messageSMS.putExtra("sms_body", "Welcome to The International ")
        startActivity(messageSMS)
    }

    fun showImage() {
        var imageIntent: Intent = Intent()
        imageIntent.type = "image/picture/*"
        imageIntent.action = Intent.ACTION_GET_CONTENT
        startActivity(imageIntent)
    }

    fun showContact() {
        val myData = "content://contacts/people/"
        val contactIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(myData)
        )
        startActivity(contactIntent)
    }

    fun show8Contact() {
        val myData = "content://contacts/people/8"

        val contact8Intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(myData)
        )
        startActivity(contact8Intent)
    }

    fun showMap() {
        val geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh"
        val mapIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(geoCode)
        )

        startActivity(mapIntent)

    }

    fun showMusic() {

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(""), "audio/*")
        startActivity(intent)

    }

    fun boardCastIntent() {
        val customIntent = Intent("CUSTOM_EVENT")
        customIntent.putExtra("broadcastMessage", "This is a custom broadcast event.")
        sendBroadcast(customIntent)
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun IntentTest() {
//    val (message, setMessage) = remember {
//        mutableStateOf("")
//    }
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column {
//            Text(text = "Message: ")
//            TextField(value = message, onValueChange = {
//                setMessage(it)
//            })
//        }
//
//        Button(onClick = { sendMessageToOtherActivity(message) }) {
//            Text(text = "Send message to other activity")
//        }
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "intent2")
//        }
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "intent3")
//        }
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "intent4")
//        }
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "intent5")
//        }
//    }
//}
