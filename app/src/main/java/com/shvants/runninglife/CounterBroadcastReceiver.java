package com.shvants.runninglife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CounterBroadcastReceiver extends BroadcastReceiver {

    public static final String HEX_COUNTER_VALUE = "Counter value:  ";
    public static final String RESULT = "RESULT";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String value = HEX_COUNTER_VALUE + intent.getStringExtra(RESULT);
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }
}
