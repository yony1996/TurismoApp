package com.example.turismoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.turismoapp.Database.DBHoteles;
import com.example.turismoapp.Helpers.Utils;
import com.example.turismoapp.databinding.ActivityInsertBinding;
import com.google.android.material.snackbar.Snackbar;

public class InsertActivity extends AppCompatActivity {
    ActivityInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        String[] accesibilidad={"1.1","1.2","1.3","1.4","1.5","1.6","1.7","1.8","1.9",
                                "2.1","2.2","2.3","2.4","2.5","2.6","2.7","2.8","2.9",
                                "3.1","3.2","3.3","3.4","3.5","3.6","3.7","3.8","3.9",
                                "4.1","4.2","4.3","4.4","4.5","4.6","4.7","4.8","4.9",
                                "5.1","5.2","5.3","5.4","5.5","5.6","5.7","5.8","5.9",
                                "6.1","6.2","6.3","6.4","6.5","6.6","6.7","6.8","6.9",
                                "7.1","7.2","7.3","7.4","7.5","7.6","7.7","7.8","7.9",
                                "8.1","8.2","8.3","8.4","8.5","8.6","8.7","8.8","8.9",
                                "9.1","9.2","9.3","9.4","9.5","9.6","9.7","9.8","9.9",
                                "10"};
        binding.hotelAccesibilidad.setAdapter(new ArrayAdapter<String>(InsertActivity.this, android.R.layout.simple_spinner_dropdown_item,accesibilidad));
        binding.chosse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        binding.saveInsert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Utils utils = new Utils();
                String encodeImage = utils.imageToBase64(binding.photo);
                binding.image.setText(encodeImage);
                int habitaciones=Integer.parseInt(binding.hotelHabitaciones.getText().toString());
                float precio=Integer.valueOf(binding.hotelPrecio.getText().toString());
                DBHoteles dbHoteles = new DBHoteles(InsertActivity.this);
                long id = dbHoteles.insertHotel(
                        binding.hotelNombre.getText().toString().trim(),
                        binding.hotelCategoria.getText().toString().trim(),
                        binding.hotelAccesibilidad.getSelectedItem().toString(),
                        binding.hotelReferencia.getText().toString().trim(),
                        habitaciones,
                        precio,
                        binding.hotelTelefono1.getText().toString().trim(),
                        binding.hotelTelefono2.getText().toString().trim(),
                        binding.hotelDireccion.getText().toString().trim(),
                        binding.hotelUrl.getText().toString().trim(),
                        binding.hotelDescripcion.getText().toString().trim(),
                        binding.image.getText().toString().trim()
                );
                if (id > 0) {
                    LimpiarInsert();
                    Snackbar.make(binding.insertLayout, "Datos guardados", Snackbar.LENGTH_SHORT).setBackgroundTint(getColor(R.color.green)).show();
                } else {
                    Snackbar.make(binding.insertLayout, "Ooops! ocurrio un error", Snackbar.LENGTH_SHORT).setBackgroundTint(getColor(R.color.red)).show();
                }

            }
        });


    }

    @SuppressWarnings("deprecation")
    public void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "seleccione la aplicacion"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            binding.photo.setImageURI(path);
        }
    }

    public void LimpiarInsert() {
        binding.hotelNombre.setText("");
        binding.hotelDireccion.setText("");
        binding.hotelUrl.setText("");
        binding.hotelTelefono1.setText("");
        binding.hotelTelefono2.setText("");
        binding.hotelDescripcion.setText("");
        binding.image.setText("");
        binding.photo.setImageResource(R.drawable.ic_add_photo);
    }
}