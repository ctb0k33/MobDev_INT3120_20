package com.example.week10

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class ContentProviderActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UI()

        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UI() {
    val context = LocalContext.current
    val resolver = context.contentResolver
    var productName by remember {
        mutableStateOf("")
    }
    var productPrice by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(text = "Product Name: ")
            TextField(value = productName, onValueChange = {
                productName = it
            })
        }
        Row() {
            Text(text = "Product Price: ")
            TextField(value = productPrice, onValueChange = {
                productPrice = it
            })
        }
        IconButton(onClick = {
            val newProduct = mapOf(productName to productPrice)
            productName = ""
            productPrice = ""
        }) {
            Image(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "")
        }
        Button(onClick = {
            if (productName.isNotEmpty() && productPrice.isNotEmpty()) {
                //Key-value object to add value in the database

                val values = ContentValues()

                values.put(ProductsProvider.name, productName)
                values.put(ProductsProvider.price, productPrice)
                val uri = resolver.insert(ProductsProvider.CONTENT_URI, values)
                Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "ADD NEW PRODUCT")
        }
    }
}
