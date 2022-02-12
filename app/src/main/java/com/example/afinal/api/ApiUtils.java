package com.example.afinal.api;

public class ApiUtils {
    public static final String BASE_URL_API = "http://192.168.43.194:8079/lomba/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService(){
        return ApiBuilder.getClient(BASE_URL_API).create(ApiService.class);
    }
}
