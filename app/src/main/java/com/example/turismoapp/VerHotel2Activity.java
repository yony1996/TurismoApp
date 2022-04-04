package com.example.turismoapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.Helpers.Utils;
import com.example.turismoapp.databinding.ActivityVerHotel2Binding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VerHotel2Activity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityVerHotel2Binding binding;
    GoogleMap map;
    int id = 0;
    Hoteles hotel;
    @SuppressLint({"StringFormatMatches", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVerHotel2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }
        DBHoteles dbHoteles = new DBHoteles(VerHotel2Activity.this);
        hotel = dbHoteles.verHoteles(id);
        if (hotel != null) {
            Utils utils = new Utils();
            Bitmap image = utils.stringImageToByte(hotel.getImage());
            binding.imageHotel.setImageBitmap(image);
            binding.hotelnombre.setText(hotel.getNombre());
            binding.hotelAccesibilidad.setText(hotel.getAccecibilidad());
            binding.hotelCategoria.setText(hotel.getCategoria());
            binding.hotelPrecio.setText(getString(R.string.precio, hotel.getPrecio()));
            binding.hoteldireccion.setText(hotel.getDireccion());
            binding.hotelDescripcion.setText(hotel.getDescripcion());
            binding.hotelHabitaciones.setText(getString(R.string.habitaciones_accesibles,hotel.getHabitaciones().toString()));
            binding.web.setText(hotel.getUrl());
            binding.telefono.setText(getString(R.string.telefono,hotel.getTelefono1(),hotel.getTelefono2()));
        }

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        assert mapFragment != null;
        mapFragment.getMapAsync( this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng ubi=new LatLng(-1.397033,-78.4305817);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ubi,10));
        MarkerOptions ubicacion=new MarkerOptions().title("hotel").position(ubi).icon(BitmapDescriptorFactory.fromResource(R.drawable.hotel));
        map.addMarker(ubicacion);
    }

}