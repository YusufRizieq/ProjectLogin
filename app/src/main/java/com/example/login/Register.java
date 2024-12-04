package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText inpEmail, inpUsername, inpPassword;
    Button btnRegister;

    // List untuk menyimpan data pengguna
    ArrayList<HashMap<String, String>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Atur padding untuk Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi elemen UI
        inpEmail = findViewById(R.id.inpEmail);
        inpUsername = findViewById(R.id.inpUsername);
        inpPassword = findViewById(R.id.inpPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Inisialisasi daftar pengguna
        userList = new ArrayList<>();

        // Logika tombol register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inpEmail.getText().toString().trim();
                String username = inpUsername.getText().toString().trim();
                String password = inpPassword.getText().toString().trim();

                // Validasi input
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Register.this, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(Register.this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Simpan data pengguna
                boolean isSaved = saveUser(email, username, password);
                if (isSaved) {
                    Toast.makeText(Register.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                    finish(); // Kembali ke aktivitas sebelumnya
                } else {
                    Toast.makeText(Register.this, "Email sudah terdaftar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Fungsi untuk menyimpan data pengguna ke daftar
    private boolean saveUser(String email, String username, String password) {
        // Periksa apakah email sudah ada di daftar
        for (HashMap<String, String> user : userList) {
            if (user.get("email").equals(email)) {
                return false; // Email sudah terdaftar
            }
        }

        // Simpan data pengguna ke daftar
        HashMap<String, String> newUser = new HashMap<>();
        newUser.put("email", email);
        newUser.put("username", username);
        newUser.put("password", password);
        userList.add(newUser);
        return true;
    }
}
