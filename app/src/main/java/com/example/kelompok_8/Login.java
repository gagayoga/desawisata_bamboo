package com.example.kelompok_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.IOException;

import com.example.kelompok_8.databinding.ActivityLoginBinding;
import com.example.kelompok_8.login.LoginRequest;
import com.example.kelompok_8.login.LoginResponse;
import com.example.kelompok_8.login.ApiClient;
import com.example.kelompok_8.login.LoginResponseErrors;
import com.example.kelompok_8.login.UserServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    TextInputEditText edtEmail, edtPassword;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        databaseHelper = new DatabaseHelper(this);

        edtEmail = findViewById(R.id.loginEmail);
        edtPassword = findViewById(R.id.login_password);

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateInput();
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void isLogin(){
        String emailInput, passwordInput;
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();

        LoginRequest loginrequest = new LoginRequest();
        loginrequest.setEmail(emailInput);
        loginrequest.setPassword(passwordInput);

        retrofit2.Call<LoginResponse> loginResponseCall = ApiClient.getUserServices().userLogin(loginrequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    LoginResponse loginResponse = response.body();

                    String username = loginResponse.getData().getName();


                    // Mendapatkan token dari response
                    String token = loginResponse.getData().getToken();

                    // Menyimpan token ke SharedPreferences
                    saveUserToken(token, username);

                    Toast.makeText(Login.this, "Login Succesfully, Selamat Datang Kembali " + username, Toast.LENGTH_SHORT).show();
                    Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentHome);
                    finish();
                }else{
                    Gson gson = new Gson();
                    try {
                        // Kode untuk mengonversi response errorBody() ke objek LoginResponseErrors
                        LoginResponseErrors errorsResponse = gson.fromJson(response.errorBody().charStream(), LoginResponseErrors.class);

                        String errorMessage = errorsResponse.getMessage(); // Ambil pesan kesalahan dari respons

                        // Tampilkan pesan kesalahan ke pengguna menggunakan Toast
                        Toast.makeText(Login.this, errorMessage, Toast.LENGTH_SHORT).show();

                        // Fokuskan kembali ke field email jika diperlukan
                        if (errorMessage != null && errorMessage.contains("Email")) {
                            edtEmail.requestFocus(); // Fokuskan kembali ke field email jika pesan berisi "Email"
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Tangani kesalahan saat membaca errorBody()
                        Toast.makeText(Login.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Login failed, please cek koneksi internet Anda.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ValidateInput(){
        String emailInput, passwordInput;
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();

        // validasi inputan kosong
        if(TextUtils.isEmpty(emailInput)){
            edtEmail.setError("Email wajib di isi!");
            edtEmail.setFocusable(true);
            return;
        } else if(TextUtils.isEmpty(passwordInput)){
            edtPassword.setError("Password wajib di isi!");
            edtPassword.setFocusable(true);
            return;
        }else if (!isValidEmail(emailInput)) {
            edtEmail.setError("Email harus berakhir dengan @gmail.com!");
            edtEmail.setFocusable(true);
            return;
        }
        else{
            isLogin();
        }
    }

    // Fungsi untuk memeriksa validitas email dengan ekspresi reguler
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_]+@gmail\\.com$";
        return email.matches(emailPattern);
    }

    // Metode untuk menyimpan token pengguna ke SharedPreferences
    private void saveUserToken(String token, String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_token", token);
        editor.putString("username", username);
        editor.apply();
    }
}