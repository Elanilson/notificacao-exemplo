package com.apkdoandroid.notificacaoapp.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.apkdoandroid.notificacaoapp.R;
import com.apkdoandroid.notificacaoapp.SegundaTelaActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage notificacao) {
        //chamado quando recebe uma notificação
        if(notificacao.getNotification() != null){
            String titulo = notificacao.getNotification().getTitle();
            String mensagem = notificacao.getNotification().getBody();

            enviarNotificacao(titulo,mensagem);


        }
        System.out.println("DegubApk:  Notificação recebida!");
    }



    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        //cria um token  em uma unica vez quando o app e instalado
        System.out.println("DegubApk:  token: "+token);
    }

    private void enviarNotificacao(String titulo,String mensagem) {
        //configurações para notificaçao
        String canal =getString(R.string.default_notification_channel_id);
        Uri uriSom = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent intent = new Intent(this, SegundaTelaActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        //criar notificação
        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(this,canal)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setSound(uriSom)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //recuperar notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //verificar versao do android
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(canal,"canal",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

        }

        //Enviar notificação
        notificationManager.notify(0,notificacao.build());
    }
}
