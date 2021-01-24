
package com.lafg.authentication_app.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("ok")
    @Expose
    private boolean ok;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;
    @SerializedName("token")
    @Expose
    private String token;

    public ResponseLogin() {}

    public ResponseLogin(boolean ok, Usuario usuario, String token) {
        super();
        this.ok = ok;
        this.usuario = usuario;
        this.token = token;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
