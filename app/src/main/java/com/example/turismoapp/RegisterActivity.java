package com.example.turismoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                DBHoteles dbHoteles = new DBHoteles(RegisterActivity.this);
                long id = dbHoteles.registerUser(
                        binding.ci.getText().toString().trim(),
                        binding.nombre.getText().toString().trim(),
                        binding.email.getText().toString().trim(),
                        binding.contrasena.getText().toString().trim(),
                        "usuario"
                );
                if (id > 0) {
                    Limpiar();
                    Snackbar.make(binding.registerLayout, "Datos guardados", Snackbar.LENGTH_SHORT).setBackgroundTint(getColor(R.color.green)).show();
                    Intent intent = new Intent(RegisterActivity.this, HotelsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Snackbar.make(binding.registerLayout, "Ooops! ocurrio un error", Snackbar.LENGTH_SHORT).setBackgroundTint(getColor(R.color.red)).show();
                }
            }
        });

        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void Limpiar() {
        binding.ci.setText("");
        binding.nombre.setText("");
        binding.email.setText("");
        binding.contrasena.setText("");
    }
}