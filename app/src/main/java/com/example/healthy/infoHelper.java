package com.example.healthy;

import android.graphics.drawable.GradientDrawable;

public class infoHelper {
    int image;
    String title;
    GradientDrawable colour;

    public infoHelper(GradientDrawable colour,int image, String title ) {
        this.image = image;
        this.title = title;
        this.colour = colour;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GradientDrawable getColour() {
        return colour;
    }

    public void setColour(GradientDrawable colour) {
        this.colour = colour;
    }
}
