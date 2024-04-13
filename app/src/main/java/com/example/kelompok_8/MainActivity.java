package com.example.kelompok_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kelompok_8.Adapter.ProductAdapter;
import com.example.kelompok_8.Adapter.ProductItemAdapter;
import com.example.kelompok_8.Interface.ProductItemClickListener;
import com.example.kelompok_8.login.LogoutTask;
import com.example.kelompok_8.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<com.example.kelompok_8.model.Product> productItemList;

    private TextView username;

    private ImageView logoutButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Image Slider
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> productList = new ArrayList<>();
        productList.add(R.drawable.slider1);
        productList.add(R.drawable.slider2);
        productList.add(R.drawable.slider3);
        // Tambahkan produk lainnya sesuai kebutuhan

        ProductAdapter adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
        //

        // Get Username
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String usernameUserr = sharedPreferences.getString("username", "");

        username = findViewById(R.id.usernameText);
        username.setText("Hallo " + usernameUserr.toString());

        // Button Logout
        logoutButton = findViewById(R.id.logoutButton);

        // List Product
        ListView listViewProducts = findViewById(R.id.listViewProducts);

        // Inisialisasi list produk
        productItemList = new ArrayList<>();
        // Tambahkan produk ke list
        // Tambahkan produk ke productItemList
        productItemList.add(new Product("Rosin Hotel Resort", "Desa wisata merupakan sebuah " +
                "konsep pariwisata yang menekankan pada pengembangan " +
                "dan pemberdayaan potensi wisata di desa-desa.", "Rp. 500.000", R.drawable.item1));
        productItemList.add(new Product("Homestay Kawasan Deswita Karang Asem", "Desa wisata merupakan sebuah " +
                "konsep pariwisata yang menekankan pada pengembangan " +
                "dan pemberdayaan potensi wisata di desa-desa.", "Rp. 300.000", R.drawable.item2));
        productItemList.add(new Product("Almiya Homestay", "Desa wisata merupakan sebuah " +
                "konsep pariwisata yang menekankan pada pengembangan " +
                "dan pemberdayaan potensi wisata di desa-desa. Desa wisata merupakan sebuah Desa wisata merupakan sebuah Desa wisata merupakan sebuah", "Rp. 100.000", R.drawable.item3));


        // Inisialisasi adapter
        ProductItemAdapter adapterItem = new ProductItemAdapter(this, productItemList, new ProductItemClickListener() {
            @Override
            public void onProductItemClick(Product product) {
                // Navigasi ke halaman detail produk dan kirimkan data produk yang dipilih
                Intent intent = new Intent(MainActivity.this, DetailProduct.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        listViewProducts.setAdapter(adapterItem);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LogoutTask(MainActivity.this).execute();
            }
        });
    }
}