package com.example.estiamapp.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.estiamapp.MainActivity
import com.example.estiamapp.R

object NotificationHelper {
    const val CHANNEL_ID = "demo_channel"

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Notification Demo",
            NotificationManager.IMPORTANCE_HIGH
        ).apply { description = "Example notification channel" }

        val nm = context.getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(channel)
    }

    fun canPostNotifications(context: Context): Boolean {
        val hasRuntimePermission = Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        val appChannelEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()

        return hasRuntimePermission && appChannelEnabled
    }

    fun show(context: Context, title: String, message: String, notificationId: Int = 1): Boolean {
        if (!canPostNotifications(context)) return false

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        return try {
            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
            true
        } catch (se: SecurityException) {
            false
        }
    }
}