package com.shvants.runninglife.utils;

import android.util.AttributeSet;

public class Const {

    public static final int ZERO = 0;
    public static final int TEN = 10;
    public static final double ZERO_DOUBLE = 0.0;
    public static final String DOT = ".";
    public static final String COLON = ":";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String HYPHEN = "-";
    public static final String SLASH = "/";
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
        String ATHLETE_ID = "ATHLETE_ID";
        String FULLNAME = "FULLNAME";
        String PROFILE_MEDIUM = "PROFILE_MEDIUM";
        String PROFILE = "PROFILE";
        String PASSWORD = "PASSWORD";
        String LOCATION = "LOCATION";
        String SEX = "SEX";
        String SUMMIT = "SUMMIT";

        String MOVING_TIME = "MOVING_TIME";
        String DISTANCE = "DISTANCE";
        String NAME = "NAME";
        String TYPE = "TYPE";
        String START_DATE = "START_DATE";
        String AVG_SPEED = "AVG_SPEED";
        String MAP = "MAP";
        String ELEVATION = "ELEVATION";
        String CALORIES = "CALORIES";
        String AVG_HR = "AVG_HR";
        String AVG_WATTS = "AVG_WATTS";

        int DATABASE_VERSION = 1;
        String DATABASE_NAME = "RUNNING_LIFE";
        String PRIMARY_KEY = "PRIMARY KEY";
        String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s);";
        String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s";
    }

}
