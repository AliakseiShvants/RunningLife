package com.shvants.runninglife.utils;

import android.util.AttributeSet;

public class Const {

    public static final int ZERO = 0;
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String INTEGER = "INTEGER";
    public static final String REAL = "REAL";
    public static final String LONG = "LONG";
    public static final String STRING = "STRING";
    public static final AttributeSet NULL = null;

    public interface FeedFragment {
        String TITLE = "Feed";
    }

    public interface ClubsFragment {
        String TITLE = "Clubs";
    }

    public interface MoveRecordFragment {
        String TITLE = "Move record";
    }

    public interface Database {
        String USER_TABLE_NAME = "USER";
        String MOVE_TABLE_NAME = "MOVE";

        String ID = "ID";
        String FULLNAME = "FULLNAME";
        String PASSWORD = "PASSWORD";
        String LOCATION = "LOCATION";

        String TITLE = "TITLE";
        String DISTANCE = "DISTANCE";
        String TIME = "TIME";
        String CALORIES = "CALORIES";
        String ELEVATION = "ELEVATION";
        String HR = "HR";

        int DATABASE_VERSION = 1;
        String DATABASE_NAME = "RUNNING_LIFE";
        String PRIMARY_KEY = "PRIMARY KEY";
        String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s);";
        String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s";
    }
}
