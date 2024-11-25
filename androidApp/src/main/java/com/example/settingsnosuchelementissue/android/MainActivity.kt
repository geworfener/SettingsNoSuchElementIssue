package com.example.settingsnosuchelementissue.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.coroutineScope
import com.example.settingsnosuchelementissue.Greeting
import com.example.settingsnosuchelementissue.MyItemDto
import com.example.settingsnosuchelementissue.MyStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        storeEmptyItems()

        storeEmptyAndNonEmptyItems()

        storeEmptyItemsTwiceCrashes()
    }

    private fun storeEmptyItems() {
        lifecycle.coroutineScope.launch {
            val storage = MyStorage()

            storage.replaceMyItems(emptyList())

            delay(1000)

            val storedItems = storage.getMyItems()

            Log.d("Storage", "Stored items should be empty: $storedItems")
        }
    }

    private fun storeEmptyAndNonEmptyItems() {
        lifecycle.coroutineScope.launch {
            val storage = MyStorage()

            storage.replaceMyItems(emptyList())

            delay(1000)

            storage.replaceMyItems(
                listOf(
                    MyItemDto(
                        name = "Name",
                        id = "Id",
                    )
                )
            )

            val storedItems = storage.getMyItems()

            Log.d("Storage", "Stored items should be 1: $storedItems")
        }
    }

    private fun storeEmptyItemsTwiceCrashes() {
        lifecycle.coroutineScope.launch {
            val storage = MyStorage()

            storage.replaceMyItems(emptyList())

            delay(1000)

            storage.replaceMyItems(emptyList())

            val storedItems = storage.getMyItems()

            Log.d("Storage", "Stored items should be empty: $storedItems")
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
