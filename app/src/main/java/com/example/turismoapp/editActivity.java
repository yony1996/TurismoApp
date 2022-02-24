package com.example.turismoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.databinding.ActivityEditBinding;

public class editActivity extends AppCompatActivity {
    ActivityEditBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}