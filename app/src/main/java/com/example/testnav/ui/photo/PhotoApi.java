package com.example.testnav.ui.photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApi {
    String BASE_URL = "https://thesaltyfish.000webhostapp.com/";

    @GET("Photos.php")
    Call<List<Photo>> getAllPhotos();
}
