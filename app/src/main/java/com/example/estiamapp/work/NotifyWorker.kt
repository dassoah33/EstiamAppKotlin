package com.example.estiamapp.work

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.estiamapp.notifications.NotificationHelper

class NotifyWorker(
    appContext: Context,
    params: WorkerParameters
): Worker (appContext, params) {
    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Worker Notification"
        val message = inputData.getString("message") ?: "Task executed"

        Log.d("NotifyWorker", "doWork is launched")
        val posted = NotificationHelper.show(applicationContext, title, message)

        Log.d("NotifyWorker", "Notification sent: $posted")

        return Result.success()
    }
}