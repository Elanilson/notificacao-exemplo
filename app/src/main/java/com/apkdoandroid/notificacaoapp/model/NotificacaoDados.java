package com.apkdoandroid.notificacaoapp.model;

import com.google.gson.annotations.SerializedName;

public class NotificacaoDados {
    @SerializedName("to")
    private String to;
    @SerializedName("notification")
    private Notificacao notification;

    public NotificacaoDados(String to, Notificacao notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notificacao getNotification() {
        return notification;
    }

    public void setNotification(Notificacao notification) {
        this.notification = notification;
    }
}
