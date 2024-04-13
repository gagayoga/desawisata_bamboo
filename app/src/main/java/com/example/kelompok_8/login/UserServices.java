package com.example.kelompok_8.login;

import com.example.kelompok_8.login.register.RegisterRequest;
import com.example.kelompok_8.login.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserServices {

    @Headers({"Accept: application/json"})

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("registrasi")
    Call<RegisterResponse> postRegister(@Body RegisterRequest registerRequest);

}
