package com.example.turismoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
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

                DBHoteles dbHoteles = new DBHoteles(InsertActivity.this);
                long id = dbHoteles.insertHotel(
                        binding.hotelNombre.getText().toString().trim(),
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