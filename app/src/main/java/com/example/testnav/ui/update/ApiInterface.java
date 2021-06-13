package com.example.testnav.ui.update;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> performUserLogIn(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("update.php")
    Call<ApiResponse> performUpdateCaption(@Field("Caption") String caption,@Field("Link_Icon") String linkIcon);

}
