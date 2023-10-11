package com.example.week12

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.week12.ui.theme.Week12Theme


class MainActivity : ComponentActivity(), SensorEventListener {
    private var humidTextContent by mutableStateOf("")
    private var srvcName = Context.TELEPHONY_SERVICE;
    private lateinit var telephonyManager: TelephonyManager
    lateinit var smsManager: SmsManager

    private var isHumiditySensorAvailable by mutableStateOf(false)
    private lateinit var sensorManager: SensorManager
    private lateinit var humiditySensor: Sensor
    override fun onCreate(savedInstanceState: Bundle?) {
        telephonyManager = getSystemService(srvcName) as TelephonyManager
        smsManager = SmsManager.getDefault()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        super.onCreate(savedInstanceState)
        // check if available
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
            isHumiditySensorAvailable = true;
        } else {
            humidTextContent = "Humidity sensor is not available"
            isHumiditySensorAvailable = false;
        }
        setContent {
            Week12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(text = "humidity: $humidTextContent")
                        Button(onClick = {
                            var callIntent: Intent =
                                Intent(Intent.ACTION_DIAL, Uri.parse("tel:0963124504"))
                            startActivity(callIntent)

                        }) {
                            Text(text = "Call alo alo")
                            Button(onClick = {
                                var sendTo: String = "5551234"
                                var myMessage: String =
                                    "Android supports programmatic SMS messaging!";
                                smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
                            }) {

                            }
                        }
                    }
                }
            }
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            humidTextContent = p0.values[0].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume();
        if (isHumiditySensorAvailable) {
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isHumiditySensorAvailable) {
            sensorManager.unregisterListener(this)
        }
    }
}


