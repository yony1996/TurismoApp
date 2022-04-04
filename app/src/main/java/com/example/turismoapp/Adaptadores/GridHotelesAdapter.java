package com.example.turismoapp.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.Helpers.Utils;
import com.example.turismoapp.R;
import com.example.turismoapp.VerHotel2Activity;

import java.util.ArrayList;

public class GridHotelesAdapter extends RecyclerView.Adapter<GridHotelesAdapter.HotelViewHolder> {
    ArrayList<Hoteles> listaHoteles;
    private  Context context;
    public GridHotelesAdapter(ArrayList<Hoteles> listaHoteles) {
        this.listaHoteles=listaHoteles;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotels_view,null, false);
        context=parent.getContext();
        return new HotelViewHolder(view);
    }
    @SuppressLint({"SetTextI18n", "StringFormatMatches"})
    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Utils utils=new Utils();
        String encodeimage=listaHoteles.get(position).getImage();
        Bitmap bitmap=utils.stringImageToByte(encodeimage);
        holder.idHotel.setText(String.valueOf(listaHoteles.get(position).getId()));
        holder.hotelNombre.setText(listaHoteles.get(position).getNombre());
        holder.hotelCategoria.setText(listaHoteles.get(position).getCategoria());
        holder.hotelAccesibilidad.setText(listaHoteles.get(position).getAccecibilidad());
        holder.hotelReferencia.setText(listaHoteles.get(position).getReferencia());
        holder.hotelHabitaciones.setText(context.getString(R.string.habitaciones,String.valueOf(listaHoteles.get(position).getHabitaciones())));
        holder.hotelPrecio.setText(context.getString(R.string.precio,Float.toString(listaHoteles.get(position).getPrecio())));
        holder.photo.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return listaHoteles.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelNombre,hotelCategoria,hotelAccesibilidad,hotelReferencia,hotelHabitaciones,hotelPrecio,idHotel;
        ImageView photo;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            idHotel=itemView.findViewById(R.id.idHotel);
            photo=itemView.findViewById(R.id.photo);
            hotelNombre=itemView.findViewById(R.id.hotelNombre);
            hotelCategoria=itemView.findViewById(R.id.hotelCategoria);
            hotelAccesibilidad=itemView.findViewById(R.id.hotelAccesibilidad);
            hotelReferencia=itemView.findViewById(R.id.hotelReferencia);
            hotelHabitaciones=itemView.findViewById(R.id.hotelHabitaciones);
            hotelPrecio=itemView.findViewById(R.id.hotelPrecio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent =new Intent(context, VerHotel2Activity.class);
                    intent.putExtra("id",listaHoteles.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
