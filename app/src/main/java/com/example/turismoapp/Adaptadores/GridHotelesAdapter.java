package com.example.turismoapp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.turismoapp.Entidades.Hoteles;
import com.example.turismoapp.Helpers.Utils;
import com.example.turismoapp.R;
import com.example.turismoapp.VerHotelActivity;

import java.util.ArrayList;

public class GridHotelesAdapter extends RecyclerView.Adapter<GridHotelesAdapter.HotelViewHolder> {
    ArrayList<Hoteles> listaHoteles;

    public GridHotelesAdapter(ArrayList<Hoteles> listaHoteles) {

        this.listaHoteles=listaHoteles;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotels,null, false);
        return new HotelViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.idHotel.setText(String.valueOf(listaHoteles.get(position).getId()));
        holder.nombreHotel.setText(listaHoteles.get(position).getNombre());
        Utils utils=new Utils();
        String encodeimage=listaHoteles.get(position).getImage();
        Bitmap bitmap=utils.stringImageToByte(encodeimage);
        holder.photo.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return listaHoteles.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        TextView nombreHotel,idHotel;
        ImageView photo;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            idHotel=itemView.findViewById(R.id.idHotel);
            photo=itemView.findViewById(R.id.photo);
            nombreHotel=itemView.findViewById(R.id.nombreHotel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent intent =new Intent(context, VerHotelActivity.class);
                    intent.putExtra("id",listaHoteles.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
