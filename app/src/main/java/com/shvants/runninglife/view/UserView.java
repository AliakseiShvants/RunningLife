package com.shvants.runninglife.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.backend.uimodels.UiUserModel;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.shvants.runninglife.utils.Const.NULL;
import static com.shvants.runninglife.utils.Const.ZERO;

public class UserView extends ConstraintLayout {

    private ImageView avatar;
    private TextView fullName;
    private TextView location;

    public UserView(final Context context) {
        this(context, NULL);
    }

    public UserView(final Context context, final AttributeSet attrs) {
        this(context, attrs, ZERO);
    }

    public UserView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUserView();
    }

    private void initUserView() {
        inflate(getContext(), R.layout.user_view, this);

        avatar = findViewById(R.id.user_avatar);
        fullName = findViewById(R.id.user_full_name);
        location = findViewById(R.id.user_location);
    }

    public void setUser(final UiUserModel user) {
        final Drawable drawable = AppCompatResources.getDrawable(getContext(), user.getAvatar());

        avatar.setImageDrawable(drawable);
        fullName.setText(user.getFullName());
        location.setText(user.getLocation());
    }
}
