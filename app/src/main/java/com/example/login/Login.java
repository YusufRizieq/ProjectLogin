package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    EditText inpUsername, inpPassword;
    Button btnLogin;
    ImageView btnBack;

    // Dummy data untuk simulasi login
    ArrayList<HashMap<String, String>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Atur padding untuk Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi elemen UI
        inpUsername = findViewById(R.id.inpUsername);
        inpPassword = findViewById(R.id.inpPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);

        // Data dummy pengguna
        userList = new ArrayList<>();
        addDummyData();

        // Tombol back untuk kembali
        btnBack.setOnClickListener(v -> finish());

        // Tombol login
        btnLogin.setOnClickListener(v -> {
            String username = inpUsername.getText().toString().trim();
            String password = inpPassword.getText().toString().trim();

            // Validasi input
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(Login.this, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validasi login
            boolean isValid = validateLogin(username, password);
            if (isValid) {
                Toast.makeText(Login.this, "Login berhasil!", Toast.LENGTH_SHORT).show();

                // Lanjut ke halaman lain (contoh: HomeActivity)
                Intent intent = new Intent(Login.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Fungsi untuk menambahkan data dummy pengguna
    private void addDummyData() {
        HashMap<String, String> user1 = new HashMap<>();
        user1.put("username", "admin");
        user1.put("password", "123456");
        userList.add(user1);

        HashMap<String, String> user2 = new HashMap<>();
        user2.put("username", "user");
        user2.put("password", "password");
        userList.add(user2);
    }

    // Fungsi untuk memvalidasi login
    private boolean validateLogin(String username, String password) {
        for (HashMap<String, String> user : userList) {
            if (user.get("username").equals(username) && user.get("password").equals(password)) {
                return true; // Login berhasil
            }
        }
        return false; // Login gagal
    }
}
