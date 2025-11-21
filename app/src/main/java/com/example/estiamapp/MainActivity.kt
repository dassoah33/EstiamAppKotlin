package com.example.estiamapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.estiamapp.notifications.NotificationHelper
import com.example.estiamapp.ui.screens.MainScreen
import com.example.estiamapp.ui.theme.EstiamAppTheme
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper.createChannel(this)

        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("FCM", "Main Activity =======> FCM TOKEN: $token")
            }
            .addOnFailureListener { exception ->
                Log.e("FCM", "Token error: ", exception)
            }

        enableEdgeToEdge()
        setContent {
            EstiamAppTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "This is onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("MainActivity", "This is onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("MainActivity", "This is onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.v("MainActivity", "This is onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("MainActivity", "This is onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("MainActivity", "This is onDestroy")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.hello_name, name),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EstiamAppTheme {
        Greeting("Android")
    }
}