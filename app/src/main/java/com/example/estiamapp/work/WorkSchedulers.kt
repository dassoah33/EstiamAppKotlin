package com.example.estiamapp.work

import android.content.Context
import androidx.work.*
import java.time.Duration

fun enqueueOneTimeAfter10Sec(context: Context) {
    val request = OneTimeWorkRequestBuilder<NotifyWorker>()
        .setInitialDelay(Duration.ofSeconds(10))
        .setInputData(workDataOf(
            "title" to "Test worker",
            "message" to "Task executed after 10 sec!!!"
        )).build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "oneTime10s",
        ExistingWorkPolicy.REPLACE,
        request
    )
}

fun enqueueOneTimeWifiCharging(context: Context) {
    val wifiAndCharging = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()

    val request = OneTimeWorkRequestBuilder<NotifyWorker>()
        .setConstraints(wifiAndCharging)
        .setInputData(workDataOf(
            "title" to "Test worker",
            "message" to "Task executed with constraint!!!"
        )).build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "oneTimeWifiCharging",
        ExistingWorkPolicy.REPLACE,
        request
    )
}