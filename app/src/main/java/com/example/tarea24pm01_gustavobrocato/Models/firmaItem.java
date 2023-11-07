package com.example.tarea24pm01_gustavobrocato.Models;

import android.graphics.Bitmap;

public class firmaItem {
    private Bitmap imageBitmap;
    private String itemDescription;

    public firmaItem(Bitmap imageBitmap, String itemDescription) {
        this.itemDescription = itemDescription;
        this.imageBitmap = imageBitmap;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
