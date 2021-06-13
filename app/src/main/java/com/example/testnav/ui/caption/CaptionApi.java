package com.example.testnav.ui.caption;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CaptionApi {
    String BASE_URL = "https://thesaltyfish.000webhostapp.com/";

    @GET("Captions.php")
    Call<List<Caption>> getAllCaptions();
}
