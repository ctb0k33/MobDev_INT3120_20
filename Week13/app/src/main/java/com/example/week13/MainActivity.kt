package com.example.week13

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.week13.ui.theme.Week13Theme

class MainActivity : ComponentActivity() {
    private var mp3url: String = "http://commondatastorage.googleapis.com/codeskulptor-assets/Epoq-Lepidoptera.ogg";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        var mediaPlayer: MediaPlayer = MediaPlayer();
        setContent {
            Week13Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(onClick = {
                            val audioAttributes = AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                            mediaPlayer.setAudioAttributes(
                                audioAttributes
                            )
                            mediaPlayer.setDataSource(mp3url)
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                        }) {
                            Text(text = "PLAY")
                        }
                        Button(onClick = {
                            mediaPlayer.pause()
                        }) {
                            Text(text = "PAUSE")
                        }
                        Button(onClick = {
                            mediaPlayer.release()
                            mediaPlayer= MediaPlayer()
                        }) {
                            Text(text = "STOP")
                        }

                    }
                }
            }
        }
    }
}



