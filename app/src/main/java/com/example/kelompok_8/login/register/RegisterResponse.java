package com.example.kelompok_8.login.register;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserData data;

    // Buat constructor, getter, dan setter sesuai kebutuhan
    public RegisterResponse(int status, String message, UserData data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    // Class untuk representasi data user
    public static class UserData {
        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email;

        @SerializedName("name")
        private String name;

        // Buat constructor, getter, dan setter sesuai kebutuhan
        public UserData(int id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

