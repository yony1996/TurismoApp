package com.example.turismoapp.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Utils {
    public String imageToBase64(ImageView image) {

        try {
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            Bitmap scalable = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            scalable.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteData = stream.toByteArray();
            String imageString = Base64.encodeToString(byteData, Base64.DEFAULT);
            return imageString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Bitmap stringImageToByte(String image) {
        byte[] imageBitmap = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(imageBitmap, 0, imageBitmap.length);
        return decodeByte;

    }



}
