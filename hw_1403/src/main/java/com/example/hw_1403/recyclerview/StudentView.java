package com.example.hw_1403.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hw_1403.R;
import com.example.hw_1403.base.CompoundRelativeLayout;

public class StudentView extends CompoundRelativeLayout {

    private TextView idView;
    private ImageView avatarView;
    private TextView nameView;
    private TextView hwCountView;

    private int attrId;
    private int attrAvatar;
    private String attrName;
    private String attrHwCount;

    public StudentView(final Context context) {
        super(context);
    }

    public StudentView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        parseAttributes(context, attrs);
    }

    public StudentView(final Context context, final AttributeSet attrs, final int defAttrs) {
        super(context, attrs, defAttrs);

        parseAttributes(context, attrs);
    }

    @Override
    public void onViewInflated(@NonNull final Context context) {

        idView = findViewById(R.id.idView);
        nameView = findViewById(R.id.nameView);
        hwCountView = findViewById(R.id.hwCountView);
        avatarView = findViewById(R.id.studentAvatarView);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_student;
    }

    @UiThread
    public StudentView setStudentId(@DrawableRes final Long id) {
        idView.setText(String.valueOf(id));

        return this;
    }

    @UiThread
    public StudentView setStudentAvatar(@DrawableRes final int avatarId) {
        avatarView.setImageResource(avatarId);

        return this;
    }

    @UiThread
    public StudentView setStudentName(final String name) {
        nameView.setText(name);

        return this;
    }

    @UiThread
    public StudentView setStudentHwCount(final int hwCount) {
        hwCountView.setText(String.valueOf(hwCount));

        return this;
    }

    private void parseAttributes(final Context context, final AttributeSet attrs) {
        final Resources.Theme theme = context.getTheme();
        final TypedArray styledAttributes = theme.obtainStyledAttributes(attrs,
                R.styleable.StudentView, 0, 0);

        try {
            attrId = styledAttributes.getInt(R.styleable.StudentView_studentId, 0);
            attrAvatar = styledAttributes.getInt(R.styleable.StudentView_studentAvatar,
                    R.drawable.icon1);
            attrName = styledAttributes.getString(R.styleable.StudentView_studentName);
            attrHwCount = styledAttributes.getString(R.styleable.StudentView_studentHwCount);

            idView.setText(String.valueOf(attrId));
            avatarView.setImageResource(attrAvatar);
            nameView.setText(attrName);
            hwCountView.setText(attrHwCount);

        } finally {
            styledAttributes.recycle();
        }
    }
}
