package com.apkdoandroid.notificacaoapp.api;

import com.apkdoandroid.notificacaoapp.model.NotificacaoDados;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificacaoService {
    @Headers({"Authorization: key=AAAAHaldnJE:APA91bHJWvkIMc0fUu9RyIFGIox7ilDHUTgeCdBhDggINvnC9fu6Co30NiqAqdrRDp9j1RjUpJKzUwT-tAnZjGmVQdvNc77rfN7FWPEeJA-LJsqHVrf2BsZGW1uOXA91NJcwQ23R12Hd ",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<NotificacaoDados>salvarNotificacao(@Body NotificacaoDados notificacaoDados);
}
