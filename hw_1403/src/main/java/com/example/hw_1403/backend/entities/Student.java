package com.example.hw_1403.backend.entities;

import android.support.annotation.DrawableRes;

import com.example.hw_1403.R;

import java.util.concurrent.atomic.AtomicLong;

public class Student {

    private final static AtomicLong ID_GENERATOR = new AtomicLong(0);

    private Long id;
    @DrawableRes
    private int avatarId;
    private String name;
    private int hwCount;

    public Student(final String name, final int hwCount) {
        id = ID_GENERATOR.getAndIncrement();
        avatarId = R.drawable.icon1;

        this.name = name;
        this.hwCount = hwCount;
    }

    public Student(final int avatarId, final String name, final int hwCount) {
        this(name, hwCount);
        this.avatarId = avatarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(@DrawableRes final int avatarId) {
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getHwCount() {
        return hwCount;
    }

    public void setHwCount(final int hwCount) {
        this.hwCount = hwCount;
    }
}
