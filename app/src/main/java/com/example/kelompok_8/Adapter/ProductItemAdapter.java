package com.example.kelompok_8.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kelompok_8.Interface.ProductItemClickListener;
import com.example.kelompok_8.R;

import java.util.List;

public class ProductItemAdapter extends ArrayAdapter<com.example.kelompok_8.model.Product> {

    private Context context;
    private List<com.example.kelompok_8.model.Product> productList;
    private ProductItemClickListener listener;

    public ProductItemAdapter(Context context, List<com.example.kelompok_8.model.Product> productList,ProductItemClickListener listener) {
        super(context, R.layout.produk_item, productList);
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        final com.example.kelompok_8.model.Product currentProduct = productList.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.produk_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // Mengatur data produk ke tampilan
        com.example.kelompok_8.model.Product product = productList.get(position);
        viewHolder.judulItem.setText(product.getJudul());
        viewHolder.deskripsiItem.setText(product.getDeskripsi());
        viewHolder.hargaItem.setText(product.getHarga());
        viewHolder.imageViewProduct.setImageResource(product.getGambarResourceId());

        // Set click listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onProductItemClick(currentProduct);
                }
            }
        });

        return view;
    }

    static class ViewHolder {
        ImageView imageViewProduct;
        TextView judulItem;
        TextView deskripsiItem;
        TextView hargaItem;

        ViewHolder(View view) {
            imageViewProduct = view.findViewById(R.id.imageViewProduct);
            judulItem = view.findViewById(R.id.judul_item);
            deskripsiItem = view.findViewById(R.id.deskripsi_item);
            hargaItem = view.findViewById(R.id.harga_item);
        }
    }
}

