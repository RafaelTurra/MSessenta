package com.lmsolutions.msessenta

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat

object NotificationUtil {

    internal val CHANNEL_ID = "1"

    fun createChannel(){
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O){
            val manager = ImSolutions.getInstance().applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = ImSolutions.getInstance().applicationContext.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(c)
        }


            }

    fun create(id: Int, intent: Intent, titulo: String, texto: String){
        createChannel()
        val context = ImSolutions.getInstance().applicationContext
        val p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(p)
            .setContentTitle(titulo)
            .setContentText(texto)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        with(NotificationManagerCompat.from(ImSolutions.getInstance())){
            val n = builder.build()
            notify(id, n)
        }

    }
}