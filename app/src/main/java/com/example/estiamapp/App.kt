package com.example.estiamapp

import android.app.Application
import android.util.Log
import com.example.estiamapp.notifications.NotificationHelper
import com.google.firebase.auth.FirebaseAuth

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        NotificationHelper.createChannel(this)

        /*FirebaseAuth.getInstance().signInAnonymously()
            .addOnSuccessListener { Log.d("Auth", "Signed In successfully: uid=${it.user?.uid}") }
            .addOnFailureListener { Log.e("Auth", "Sign-in failed", it) }*/
    }
}