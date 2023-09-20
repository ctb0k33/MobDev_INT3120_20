package com.example.week6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OptionsMenu()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OptionsMenu() {

    // Create a boolean variable
    // to store the display menu state
    var mDisplayMenu by remember { mutableStateOf(false) }
    var expandedPopUpMenu by remember { mutableStateOf(false) }
    var expandedLongCLickContextMenu by remember {
        mutableStateOf(false)
    }
    var expanedNestedContextMenu by remember {
        mutableStateOf(false)
    }
    var DemoPopUpMenuText by remember {
        mutableStateOf("Demo PopUp menu")
    }
    var LongClickPopUpMenuText by remember {
        mutableStateOf("Long Click context menu")
    }

    val mContext = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { Text("Menu Options", color = Color.Black) },
                Modifier.background(Color.Black),
                actions = {
                    Text(text = "Hello TopBar")
                    IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Icon(Icons.Default.MoreVert, "")
                    }

                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false }
                    ) {

                        DropdownMenuItem(text = { Text(text = "Settings") }, onClick = {
                            Toast.makeText(
                                mContext,
                                "Settings",
                                Toast.LENGTH_SHORT
                            ).show()
                        })

                        DropdownMenuItem(text = { Text(text = "Logout") }, onClick = {
                            Toast.makeText(
                                mContext,
                                "Logout",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    }
                }
            )
            Box {
                Button(onClick = { expandedPopUpMenu = !expandedPopUpMenu }) {
                    Text(text = DemoPopUpMenuText)

                }
                DropdownMenu(
                    expanded = expandedPopUpMenu,
                    onDismissRequest = { expandedPopUpMenu = false }
                ) {
                    DropdownMenuItem(text = { Text(text = "Java") }, onClick = {
                        DemoPopUpMenuText = "Java"
                        expandedPopUpMenu = !expandedPopUpMenu
                    })
                    Divider()
                    DropdownMenuItem(text = { Text(text = "Python") }, onClick = {
                        DemoPopUpMenuText = "Python"
                        expandedPopUpMenu = !expandedPopUpMenu
                    })
                    Divider()
                    DropdownMenuItem(text = { Text(text = "Kotlin") }, onClick = {
                        DemoPopUpMenuText = "Kotlin"
                        expandedPopUpMenu = !expandedPopUpMenu
                    })
                    Divider()
                    DropdownMenuItem(text = { Text(text = "None") }, onClick = {
                        DemoPopUpMenuText = "Demo PopUp Menu"
                        expandedPopUpMenu = !expandedPopUpMenu
                    })
                }
            }
            Box {
                Surface(color = Color.Cyan, modifier = Modifier
                    .combinedClickable(
                        onLongClick = {
                            expandedLongCLickContextMenu = !expandedLongCLickContextMenu
                        },
                    ) {}
                ) {
                    Text(text = LongClickPopUpMenuText, Modifier.padding(20.dp))
                }
                DropdownMenu(
                    expanded = expandedLongCLickContextMenu,
                    onDismissRequest = { expandedLongCLickContextMenu = false }
                ) {

                    DropdownMenuItem(text = { Text(text = "Social Media") }, onClick = {
                        expanedNestedContextMenu = !expanedNestedContextMenu
                    })

                    Divider()
                    DropdownMenuItem(text = { Text(text = "Python") }, onClick = {
                        expandedLongCLickContextMenu = !expandedLongCLickContextMenu
                        LongClickPopUpMenuText = "Python"
                    })
                    Divider()
                }
                DropdownMenu(
                    expanded = expanedNestedContextMenu,
                    onDismissRequest = { expanedNestedContextMenu = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "FaceBook") },
                        onClick = {
                            expanedNestedContextMenu = !expanedNestedContextMenu
                            expandedLongCLickContextMenu = !expandedLongCLickContextMenu
                            LongClickPopUpMenuText = "Social media Facebook"
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Instagram") },
                        onClick = {
                            expanedNestedContextMenu = !expanedNestedContextMenu
                            expandedLongCLickContextMenu = !expandedLongCLickContextMenu
                            LongClickPopUpMenuText = "Social media Instagram"
                        },
                    )
                }
            }


        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OptionsMenu()
}
