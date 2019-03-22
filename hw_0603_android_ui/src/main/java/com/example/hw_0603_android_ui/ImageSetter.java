package com.example.hw_0603_android_ui;

import android.view.View;
import android.widget.ImageView;

public class ImageSetter {

    private View[] views;
    private int[] imageViewIds;

    public ImageSetter(final View[] views, final int[] imageViewIds) {
        this.views = views;
        this.imageViewIds = imageViewIds;
    }

    public void setImageToViews() {

        for (int i = 0; i < views.length; i++) {
            ((ImageView) views[i].findViewById(R.id.image)).setImageResource(imageViewIds[i]);
        }
    }
}
