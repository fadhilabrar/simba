package com.example.afinal.login.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "session";
    private String nama = "nama";
    private String email = "email";
    private String login = "login";
    private String no_telp = "no_telp";
    private String alamat = "alamat";

    public Session(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean getLogin() {
        return pref.getBoolean(login, false);
    }

    public void setLogin(boolean x) {
        editor.putBoolean(login, x);
        editor.commit();
    }

    public String getNama() {
        return pref.getString(nama,"");
    }

    public void setNama(String id) {
        editor.putString(nama,id);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(email,"");
    }

    public void setEmail(String id) {
        editor.putString(email,id);
        editor.commit();
    }

    public String getNo_telp() {
        return pref.getString(no_telp,"");
    }

    public void setNo_telp(String id) {
        editor.putString(no_telp,id);
        editor.commit();
    }

    public String getAlamat() {
        return pref.getString(alamat,"");
    }

    public void setAlamat(String id) {
        editor.putString(alamat,id);
        editor.commit();
    }

}
