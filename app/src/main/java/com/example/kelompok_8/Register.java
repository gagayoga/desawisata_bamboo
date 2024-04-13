package com.example.kelompok_8;

import static android.text.TextUtils.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kelompok_8.databinding.ActivityRegisterBinding;
import com.example.kelompok_8.login.ApiClient;
import com.example.kelompok_8.login.LoginResponse;
import com.example.kelompok_8.login.register.RegisterRequest;
import com.example.kelompok_8.login.register.RegisterResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    ImageView back;
    ActivityRegisterBinding binding;
    DatabaseHelper databaseHelper;

    TextInputEditText edtEmail, edtPassword, edtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        back = findViewById(R.id.kembali);
        back.setOnClickListener(view -> {
            Intent i = new Intent(Register.this, Login.class);
            startActivity(i);
        });

        databaseHelper = new DatabaseHelper(this);

        edtEmail = findViewById(R.id.signup_email);
        edtPassword = findViewById(R.id.signup_password);
        edtUsername = findViewById(R.id.signup_username);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }

        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    private void validateInput(){
        String emailInput, passwordInput, usernameInput;
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();
        usernameInput = edtUsername.getText().toString().trim();

        // validasi inputan kosong
        if(TextUtils.isEmpty(emailInput)){
            edtEmail.setError("Email wajib di isi!");
            edtEmail.setFocusable(true);
            return;
        } else if(TextUtils.isEmpty(passwordInput)){
            edtPassword.setError("Password wajib di isi!");
            edtPassword.setFocusable(true);
            return;
        }else if(TextUtils.isEmpty(usernameInput)){
            edtPassword.setError("Username wajib di isi!");
            edtPassword.setFocusable(true);
            return;
        }
        else{
            postRegister();
        }
    }

    private void postRegister(){
        String emailInput, passwordInput, usernameInput;
        emailInput = edtEmail.getText().toString().trim();
        passwordInput = edtPassword.getText().toString().trim();
        usernameInput = edtUsername.getText().toString().trim();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(usernameInput);
        registerRequest.setEmail(emailInput);
        registerRequest.setPassword(passwordInput);

        Call<RegisterResponse> loginResponseCall = ApiClient.getUserServices().postRegister(registerRequest);

        loginResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null) {
                        // Proses respons sukses sesuai kebutuhan
                        int status = registerResponse.getStatus();
                        String message = registerResponse.getMessage();

                        if (status == 201) {
                            // Registrasi berhasil, tampilkan pesan berhasil
                            Toast.makeText(getApplicationContext(), "Registrasi Berhasil, Silakan login dahulu " + usernameInput, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Tampilkan pesan error jika respons status bukan 201
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Respons tidak sukses (status tidak 2xx)
                    // Handle error jika diperlukan
                    Toast.makeText(getApplicationContext(), "Registrasi gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Handle ketika terjadi error pada proses jaringan atau lainnya
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}