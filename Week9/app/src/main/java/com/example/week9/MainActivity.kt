package com.example.week9

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaParser.InputReader
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.InputStreamReader
import java.lang.StringBuilder


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var settings: SharedPreferences = getSharedPreferences("name", 0);

        setContent {
            var prefVal = remember {
                mutableStateOf("")
            }
            var fileText = remember {
                mutableStateOf("")
            }
            var fileTextOutPut = remember {
                mutableStateOf("")
            }
            var fileExternalName: String = "external.txt";
            var fileExternalPath: String = "myFileDir";
            var fileExternalContent = remember {
                mutableStateOf("Init value")
            }
            var fileExternalInput = remember {
                mutableStateOf("")
            };

            Column {
                Button(onClick = { createShareRef() }) {
                    Text(text = "Create Preferences")
                }
                Button(onClick = {
                    prefVal.value = getSharePreVal() ?: "no name"
                }) {
                    Text(text = "get Pref Value")
                }
                Column {
                    Text(
                        text = "user name share preference: ${
                            prefVal.value
                        }"
                    )
                }
                Button(onClick = {
                    val fileinputStream: FileInputStream = openFileInput("myfile.txt")
                    var inputStreamReader: InputStreamReader = InputStreamReader(fileinputStream)
                    var bufferReader: BufferedReader = BufferedReader(inputStreamReader)
                    var stringBuffer: StringBuffer = StringBuffer()
                    var lines: String?
                    while (bufferReader.readLine().also { lines = it } != null) {
                        lines?.let {
                            stringBuffer.append(lines + "\n")
                        }
                    }
                    fileTextOutPut.value = stringBuffer.toString()
                }) {
                    Text(text = "Read FIle")
                }
                Button(onClick = {
                    val fileOutputStream: FileOutputStream = openFileOutput(
                        "myfile.txt",
                        MODE_PRIVATE
                    )
                    fileOutputStream.write(fileText.value.toByteArray())
                    fileOutputStream.close()
                }) {
                    Text(text = "Write File")
                }
                Text(text = "File Content: ${fileTextOutPut.value}")
                TextField(value = fileExternalName, onValueChange = {fileExternalName = it} )
                Text(
                    text = "External storage demo", style = TextStyle(
                        fontSize = 24.sp
                    )
                )
                TextField(value = fileText.value, onValueChange = { fileText.value = it })
                Text(text = "external file content:${fileExternalContent.value}")
                Button(onClick = {
                    fileExternalContent.value = fileExternalInput.value.trim()
                    if (!fileExternalContent.equals("")) {
                        var myExternalFile: File =
                            File(getExternalFilesDir(fileExternalPath), fileExternalName)
                        var fileOutputStream: FileOutputStream? = null;

                        fileOutputStream = FileOutputStream(myExternalFile)
                        fileOutputStream.write(fileExternalContent.value.toByteArray())


                    }
                }, enabled = isExternalStorageAvailable()) {
                    Text(text = "Save External file")
                }
                Button(onClick = {
                    var fileReader: FileReader? = null;
                    var myExternalFile: File = File(getExternalFilesDir(fileExternalPath),fileExternalName)
                    var stringBuilder: StringBuilder = StringBuilder()
                    fileReader = FileReader(myExternalFile)
                    val bufferreader:BufferedReader = BufferedReader(fileReader)
                    var lines:String? = bufferreader.readLine();
                    while (lines !=null){
                        stringBuilder.append(lines).append("\n")
                        lines= bufferreader.readLine()
                    }
                    fileExternalContent.value = "file content\n$stringBuilder"
                }) {
                    Text(text = "Load External file")
                }
                TextField(
                    value = fileExternalInput.value,
                    onValueChange = { fileExternalInput.value = it }
                )

            }

        }
    }

    private fun createShareRef() {
        val settings: SharedPreferences = getSharedPreferences("name", 0);`
        val editor: SharedPreferences.Editor = settings.edit();
        editor.putString("userName", "Thang");
        editor.apply()
    }

    fun getSharePreVal(): String? {
        val settings: SharedPreferences = getSharedPreferences("name", 0);
        var prefVal = settings.getString("userName", "noName")
        return prefVal
    }

    fun isExternalStorageAvailable(): Boolean {
        var externalStorageState: String = Environment.getExternalStorageState()
        return externalStorageState.equals(Environment.MEDIA_MOUNTED)
    }

}


