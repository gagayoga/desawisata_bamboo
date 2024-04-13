package com.example.kelompok_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class Splash_Screen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String userToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        userToken = getUserToken();

        Thread thread = new Thread() {
            public void run () {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    checkUser();
                }
            }
        };
        thread.start();
    }

    private void checkUser(){
        if (userToken.isEmpty()) {
            Intent intent = new Intent(Splash_Screen.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            // Menampilkan Toast di thread UI utama
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Splash_Screen.this, "Selamat Datang Kembali, Anda sudah login", Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private String getUserToken() {
        return sharedPreferences.getString("user_token", "");
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}