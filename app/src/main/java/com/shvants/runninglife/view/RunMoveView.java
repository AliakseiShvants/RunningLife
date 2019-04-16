package com.shvants.runninglife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.shvants.runninglife.R;

import androidx.constraintlayout.widget.ConstraintLayout;


public class RunMoveView extends ConstraintLayout {

    private ImageView avatar;
    private TextView fullName;
    private TextView beginTime;

    public RunMoveView(final Context context) {
        this(context, null);
    }

    public RunMoveView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RunMoveView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUserView();
    }

    private void initUserView() {
        inflate(getContext(), R.layout.user_view, this);

        avatar = findViewById(R.id.user_avatar);
        fullName = findViewById(R.id.user_full_name);
        beginTime = findViewById(R.id.user_location);
    }
}
