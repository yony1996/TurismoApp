package com.example.turismoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.databinding.ActivityHotelsBinding;
import com.example.turismoapp.databinding.ActivityVerHotelBinding;

public class VerHotelActivity extends AppCompatActivity {
    ActivityVerHotelBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityVerHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}