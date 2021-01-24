
package com.lafg.authentication_app.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("online")
    @Expose
    private boolean online;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("uid")
    @Expose
    private String uid;

    public Usuario() {}

    public Usuario(boolean online, String nombre, String email, String uid) {
        super();
        this.online = online;
        this.nombre = nombre;
        this.email = email;
        this.uid = uid;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
