package com.example.afinal.api;

import com.example.afinal.artikel.model.Artikel;
import com.example.afinal.helpers.BaseResponse;
import com.example.afinal.lomba.model.Kategori;
import com.example.afinal.lomba.model.Lomba;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("user/login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("alamat") String alamat,
                                       @Field("no_telp") String noTelp);

    @FormUrlEncoded
    @POST("lomba/insert.php")
    Call<ResponseBody> insertLomba(@Field("id_kategori") String idKategori,
                                   @Field("nama") String nama,
                                   @Field("penyelenggara") String penyelenggara,
                                   @Field("tanggal") String tanggal,
                                   @Field("tema") String tema,
                                   @Field("lokasi") String lokasi,
                                   @Field("hadiah") String hadiah,
                                   @Field("deskripsi") String deskripsi,
                                   @Field("img") String img);

    @GET("lomba/selectall.php")
    Call<BaseResponse<List<Lomba>>> getLomba();

    @GET("lomba/selectkategori.php")
    Call<BaseResponse<List<Kategori>>> getKategori();

    @GET("artikel/selectall.php")
    Call<BaseResponse<List<Artikel>>> getArtikel();

}
