package com.example.estiamapp.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("FCM", "=======> FCM TOKEN: $token")

        // In production call and API to register FCM token in user database
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("FCM", "From=${message.from}, data=${message.data}, notif=${message.notification}")

        val title = message.notification?.title ?: message.data["title"] ?: "Message"
        val body = message.notification?.body ?: message.data["body"] ?: "This is notification body"

        NotificationHelper.show(
            context = this,
            title = title,
            message = body
        )
    }
}