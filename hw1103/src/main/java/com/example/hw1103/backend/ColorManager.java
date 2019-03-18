package com.example.hw1103.backend;

import android.support.annotation.DrawableRes;

import com.example.hw1103.R;

import java.util.Random;

public class ColorManager {

    @DrawableRes
    private static int[] colors = new int[]{
            R.color.blue, R.color.brown, R.color.green, R.color.indigo, R.color.orange,
            R.color.pink, R.color.purple, R.color.teal, R.color.red, R.color.yellow,
    };

    @DrawableRes
    public static int getRandomColor(){
        Random random = new Random();
        int i = random.nextInt(10);

        return colors[i];
    }
}
