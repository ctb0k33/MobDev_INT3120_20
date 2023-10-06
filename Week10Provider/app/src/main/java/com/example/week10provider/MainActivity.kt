package com.example.week10provider

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.week10provider.ui.theme.Week10ProviderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val context = LocalContext.current
    val resolver = context.contentResolver
    var data by remember {
        mutableStateOf("")
    }
    Column {
        Text(text = data)
        Button(onClick = {
            val contactUri = Uri.parse("content://com.example.week10/products")
            val projection = null
            val selectionClause = null
            val selectionArguments = null
            val sortOrder = null
            val cursor = resolver.query(
                contactUri,
                projection,
                selectionClause,
                selectionArguments,
                sortOrder
            )
            val stringBuilder = StringBuilder()
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    stringBuilder.append("ID: ")
                    stringBuilder.append(cursor.getString(cursor.getColumnIndexOrThrow("id")))

                    stringBuilder.append("\t")
                    stringBuilder.append("\t")

                    stringBuilder.append("Name: ")
                    stringBuilder.append(cursor.getString(cursor.getColumnIndexOrThrow("name")))

                    stringBuilder.append("\t")
                    stringBuilder.append("\t")

                    stringBuilder.append("Price: ")
                    stringBuilder.append(cursor.getString(cursor.getColumnIndexOrThrow("price")))

                    stringBuilder.append("\n")
                    stringBuilder.append("\n")

                } while (cursor.moveToNext())
                //close the cursor object
                cursor.close()
            } else {
                stringBuilder.append("Noting to show")
            }
            //set string value in  textView
            data = stringBuilder.toString()
        }) {
            Text(text = "reset data")
        }
    }
}

