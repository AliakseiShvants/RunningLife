package com.shvants.runninglife;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserView extends RelativeLayout {

    private ImageView avatar;
    private TextView fullName;
    private TextView location;

    public UserView(final Context context) {
        super(context);
        initUserView();
    }

    public UserView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initUserView();
    }

    public UserView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUserView();
    }

    public UserView(final Context context, final AttributeSet attrs, final int defStyleAttr,
                    final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUserView();
    }

    private void initUserView() {
        inflate(getContext(), R.layout.user_view, this);

        avatar = findViewById(R.id.user_move_avatar);
        fullName = findViewById(R.id.user_move_full_name);
        location = findViewById(R.id.user_move_begin_time);
    }
}
