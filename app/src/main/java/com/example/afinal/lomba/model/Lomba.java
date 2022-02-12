package com.example.afinal.lomba.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Lomba implements Parcelable{
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("img")
    private String img;

    @SerializedName("penyelenggara")
    private String penyelenggara;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("tema")
    private String tema;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("hadiah")
    private String hadiah;

    protected Lomba(Parcel in) {
        id = in.readString();
        nama = in.readString();
        tema = in.readString();
        lokasi = in.readString();
        penyelenggara = in.readString();
        deskripsi = in.readString();
        hadiah = in.readString();
        img = in.readString();
        tanggal = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(tema);
        dest.writeString(lokasi);
        dest.writeString(penyelenggara);
        dest.writeString(deskripsi);
        dest.writeString(hadiah);
        dest.writeString(img);
        dest.writeString(tanggal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Lomba> CREATOR = new Parcelable.Creator<Lomba>() {
        @Override
        public Lomba createFromParcel(Parcel in) {
            return new Lomba(in);
        }

        @Override
        public Lomba[] newArray(int size) {
            return new Lomba[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHadiah() {
        return hadiah;
    }

    public void setHadiah(String hadiah) {
        this.hadiah = hadiah;
    }

}
