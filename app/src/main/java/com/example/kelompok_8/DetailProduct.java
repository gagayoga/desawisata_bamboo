package com.example.kelompok_8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelompok_8.model.Product;

public class DetailProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // Ambil data produk dari intent
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        // Set judul
        TextView judulTextView = findViewById(R.id.judul_item);
        judulTextView.setText(product.getJudul());

        // Set deskripsi
        TextView deskripsiTextView = findViewById(R.id.deskripsi_item);
        deskripsiTextView.setText(product.getDeskripsi());

        // Set harga
        TextView hargaTextView = findViewById(R.id.harga_item);
        hargaTextView.setText(product.getHarga());

        // Set gambar
        ImageView gambarImageView = findViewById(R.id.gambar_item);
        gambarImageView.setImageResource(product.getGambarResourceId());

        // Tambahkan onClickListener pada ImageView back
        ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke MainActivity
                finish();
            }
        });
    }
}
