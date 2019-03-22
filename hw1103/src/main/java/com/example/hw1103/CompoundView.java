package com.example.hw1103;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw1103.backend.User;

public class CompoundView extends LinearLayout {

    private ImageView icon;
    private TextView name;
    private TextView email;

    public CompoundView(final Context context) {
        super(context);
        initCompoundView();
    }

    public CompoundView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initCompoundView();
    }

    public CompoundView(final Context context, @Nullable final AttributeSet attrs,
                        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCompoundView();
    }

    public CompoundView(final Context context, final AttributeSet attrs, final int defStyleAttr,
                        final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initCompoundView();
    }

    private void initCompoundView() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.compound_view, this);

        icon = findViewById(R.id.compound_view_icon);
        name = findViewById(R.id.compound_view_username);
        email = findViewById(R.id.compound_view_email);
    }

    public void setUserToCompoundView(final User user) {
        final Drawable drawable = ContextCompat.getDrawable(getContext(), user.getIcon());

        icon.setImageDrawable(drawable);
        name.setText(user.getName());
        email.setText(user.getEmail());
    }

    public void changeColor(@DrawableRes final int color) {
        DrawableCompat.setTint(icon.getDrawable(), color);
    }
}
