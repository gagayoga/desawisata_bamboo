package com.example.kelompok_8.model;
import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String judul;
    private String deskripsi;
    private String harga;
    private int gambarResourceId;

    // Constructor, getters, and setters
    public Product(String judul, String deskripsi, String harga, int gambarResourceId) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.gambarResourceId = gambarResourceId;
    }

    // Getter and setter methods
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getGambarResourceId() {
        return gambarResourceId;
    }

    public void setGambarResourceId(int gambarResourceId) {
        this.gambarResourceId = gambarResourceId;
    }

    // Parcelable implementation
    protected Product(Parcel in) {
        judul = in.readString();
        deskripsi = in.readString();
        harga = in.readString();
        gambarResourceId = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(deskripsi);
        dest.writeString(harga);
        dest.writeInt(gambarResourceId);
    }
}
