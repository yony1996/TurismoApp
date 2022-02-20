package com.example.turismoapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.turismoapp.Adaptadores.GridHotelesAdapter;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.databinding.ActivityHotelsBinding;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {
    ActivityHotelsBinding binding;
    ArrayList<Hoteles> listaArrayHoteles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHotelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gridHoteles.setLayoutManager(new GridLayoutManager(this,2));
        DBHoteles dbHoteles=new DBHoteles(HotelsActivity.this);
        listaArrayHoteles=new ArrayList<>();

        GridHotelesAdapter adapter=new GridHotelesAdapter(dbHoteles.mostrarHoteles());
        binding.gridHoteles.setAdapter(adapter);
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GridHotelesAdapter adapter=new GridHotelesAdapter(dbHoteles.mostrarHoteles());
                binding.gridHoteles.setAdapter(adapter);
                binding.refresh.setRefreshing(false);
            }
        });
        binding.floatInsert.setOnClickListener(view -> {
            Intent intent=new Intent(HotelsActivity.this,InsertActivity.class);
            startActivity(intent);
        });
    }
}