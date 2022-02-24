package com.example.turismoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.Entidades.User;
import com.example.turismoapp.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                DBHoteles dbHoteles=new DBHoteles(LoginActivity.this);
                user=dbHoteles.loginUser(
                        binding.email.getText().toString().trim(),
                        binding.contrasena.getText().toString().trim()
                );
                if(user !=null){
                    Intent intent = new Intent(LoginActivity.this, HotelsActivity.class);
                    intent.putExtra("rol",user.getRol());
                    startActivity(intent);
                    finish();
                }else{
                    Snackbar.make(binding.loginLayout, "usuario no registrado o datos incorrectos", Snackbar.LENGTH_SHORT).setBackgroundTint(getColor(R.color.red)).show();
                }
            }
        });
        binding.gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}