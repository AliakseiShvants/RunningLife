package com.example.hw_0603_android_ui;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SongView extends RelativeLayout {
    public SongView(final Context context) {
        super(context);
    }

    public SongView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public SongView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SongView(final Context context, final AttributeSet attrs, final int defStyleAttr,
                    final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
