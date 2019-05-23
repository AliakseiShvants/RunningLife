package com.shvants.runninglife.utils;

import android.util.AttributeSet;

public class Const {

    public static final int ZERO = 0;
    public static final long ZERO_LONG = 0L;
    public static final double ZERO_DOUBLE = 0.0;
    public static final String DOT = ".";
    public static final String COLON = ":";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String SLASH = "/";
    public static final String INTEGER = "INTEGER";
    public static final String REAL = "REAL";
    public static final String LONG = "LONG";
    public static final String STRING = "STRING";
    public static final AttributeSet NULL = null;

    public static final String ATHLETE_ID = "ATHLETE_ID";
    public static final String FULLNAME = "FULLNAME";
    public static final String LOCATION = "LOCATION";
    public static final String PROFILE_MEDIUM = "PROFILE_MEDIUM";
    public static final String PROFILE = "PROFILE";
    public static final String SUMMIT = "SUMMIT";

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
    }

}
