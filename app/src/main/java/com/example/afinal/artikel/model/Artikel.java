package com.example.afinal.artikel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.afinal.lomba.model.Kategori;
import com.google.gson.annotations.SerializedName;

public class Artikel implements Parcelable{
    @SerializedName("id")
    private String id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("img_artikel")
    private String img;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("konten")
    private String konten;

    protected Artikel(Parcel in) {
        id = in.readString();
        judul = in.readString();
        created_at = in.readString();
        img = in.readString();
        konten = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(judul);
        dest.writeString(created_at);
        dest.writeString(img);
        dest.writeString(konten);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Artikel> CREATOR = new Parcelable.Creator<Artikel>() {
        @Override
        public Artikel createFromParcel(Parcel in) {
            return new Artikel(in);
        }

        @Override
        public Artikel[] newArray(int size) {
            return new Artikel[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

}
