package dev.android.play.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.android.play.app.HomeActivity

class FcmMessagingService : FirebaseMessagingService() {
    private val TAG = "FcmMessagingService"
    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i(TAG, "${message.from}")
        if (message.data.isNotEmpty()) {
            Log.i(TAG, "${message.from}")
            val title: String? = message.notification?.title
            val text: String? = message.notification?.body
            if (true) {
                scheduleJob()
            } else {
                sendNotification(text!!, title)
            }
            message.notification?.let {
                Log.i(TAG, "Message Notification Body :  ${it.body}")
            }
        }
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        super.onSendError(msgId, exception)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sedRegistrationToServer(token)
    }

    private fun scheduleJob() {
        val work = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(this)
            .beginWith(work)
            .enqueue()
    }

    private fun handleNow() {
        Log.i(TAG, "Short Lived Task done")
    }

    private fun sedRegistrationToServer(token: String?) {
        Log.i(TAG, "sedRegistrationToServer $token")
    }

    private fun sendNotification(messageBody: String, title: String?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val channelId = "fcm_Default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentText(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Android Play",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())


    }

    internal class MyWorker(appContext: Context, workParams: WorkerParameters) :
        Worker(appContext, workParams) {
        override fun doWork(): Result {
            return Result.success()
        }
    }


}