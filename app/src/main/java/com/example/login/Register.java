package com.example.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class Register extends AppCompatActivity implements TextWatcher {
    // Data untuk AutoComplete Email
    private AutoCompleteTextView acEmail;
    private String[] emailsugestion = {"admin@gmail.com", "admin@yahoo.com", "admin@outlook.com", "@hotmail.com"};

    // Data untuk Kota Asal
    private String[] kota = {"Yogyakarta", "Cirebon", "Kuningan", "Majalengka", "Jakarta"};
    private EditText etKotaAsal;

    // Input lainnya
    private EditText etUsername, etPassword;
    private Button btnRegister;

    // HashMap untuk menyimpan data
    private HashMap<String, String> userData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi elemen UI
        acEmail = findViewById(R.id.autoCompleteEmail);
        etUsername = findViewById(R.id.inpUsername);
        etPassword = findViewById(R.id.inpPassword);
        etKotaAsal = findViewById(R.id.listlokasi);
        btnRegister = findViewById(R.id.btnRegister);

        // Konfigurasi AutoComplete untuk Email
        ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailsugestion);
        acEmail.setAdapter(emailAdapter);
        acEmail.addTextChangedListener(this);

        // Konfigurasi dialog untuk memilih kota
        etKotaAsal.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pilih Lokasi Asal Mu");
            builder.setItems(kota, (dialogInterface, item) -> {
                etKotaAsal.setText(kota[item]);
                dialogInterface.dismiss();
            });
            builder.show();
        });

        // Event klik tombol Register
        btnRegister.setOnClickListener(v -> {
            String email = acEmail.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String kotaAsal = etKotaAsal.getText().toString().trim();

            // Validasi input
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || kotaAsal.isEmpty()) {
                Toast.makeText(Register.this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan data ke HashMap
            userData.put("email", email);
            userData.put("username", username);
            userData.put("password", password);
            userData.put("kotaAsal", kotaAsal);

            // Tampilkan pesan sukses
            Toast.makeText(Register.this, "Registrasi Berhasil!\n" + userData.toString(), Toast.LENGTH_LONG).show();

            // Reset input setelah berhasil
            acEmail.setText("");
            etUsername.setText("");
            etPassword.setText("");
            etKotaAsal.setText("");
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        // Tidak digunakan
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Tidak digunakan
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // Tidak digunakan
    }
}