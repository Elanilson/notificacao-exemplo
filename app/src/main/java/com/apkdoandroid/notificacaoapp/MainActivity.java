package com.apkdoandroid.notificacaoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apkdoandroid.notificacaoapp.api.NotificacaoService;
import com.apkdoandroid.notificacaoapp.model.Notificacao;
import com.apkdoandroid.notificacaoapp.model.NotificacaoDados;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private String baseUrl;
     String tokee = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adicionar topico
     //   FirebaseMessaging.getInstance().subscribeToTopic("brasileira");
        //remover topico
       // FirebaseMessaging.getInstance().unsubscribeFromTopic("brasileira");
        baseUrl = "https://fcm.googleapis.com/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void recuperarToken(View view){
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                tokee = token;
                System.out.println("DegubApk:  token recuperado: "+token);
            }
        });
    }

    public void enviarNotificacao(View view){
        String to =""; // topico ou token
        to = tokee;
      //  to ="/topics/brasileira";

        Notificacao notificacao = new Notificacao("Enviando retrofit","hahhahsahshashahs");
        NotificacaoDados notificacaoDados = new NotificacaoDados(to,notificacao);

        NotificacaoService service = retrofit.create(NotificacaoService.class);
        Call<NotificacaoDados> call = service.salvarNotificacao(notificacaoDados);

        call.enqueue(new Callback<NotificacaoDados>() {
            @Override
            public void onResponse(Call<NotificacaoDados> call, Response<NotificacaoDados> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Código: "+response.code(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Error: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificacaoDados> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Falha: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}