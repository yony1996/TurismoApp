package com.example.turismoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.Helpers.Utils;
import com.example.turismoapp.databinding.ActivityVerHotelBinding;
import com.google.android.material.tabs.TabLayout;

public class VerHotelActivity extends AppCompatActivity {
    ActivityVerHotelBinding binding;
    Hoteles hotel;
    int id=0;
    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityVerHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        if(savedInstanceState==null){
            Bundle extras =getIntent().getExtras();
            if (extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("id");
            }
        }else{
            id=(int) savedInstanceState.getSerializable("id");
        }
        DBHoteles dbHoteles=new DBHoteles(VerHotelActivity.this);
        hotel=dbHoteles.verHoteles(id);

        if(hotel !=null){

            Utils utils=new Utils();
            Bitmap image=utils.stringImageToByte(hotel.getImage());
            binding.imageHotel.setImageBitmap(image);
            binding.hotelNombre.setText(hotel.getNombre());
            binding.hotelDescripcion.setText(hotel.getDescripcion());
            binding.info.setText(getString(R.string.secundario,hotel.getDireccion(),hotel.getTelefono1(),hotel.getTelefono2()));
            binding.web.setText(hotel.getUrl());
            binding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()){
                        case 0:
                            binding.principal.setVisibility(View.VISIBLE);
                            binding.secundario.setVisibility(View.GONE);
                            break;
                        case 1:
                            binding.principal.setVisibility(View.GONE);
                            binding.secundario.setVisibility(View.VISIBLE);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}