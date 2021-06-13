package com.example.testnav.ui.music;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicApi {
    String BASE_URL = "https://thesaltyfish.000webhostapp.com/";

    @GET("Musics.php")
    Call<List<Music>> getAllMusics();
}