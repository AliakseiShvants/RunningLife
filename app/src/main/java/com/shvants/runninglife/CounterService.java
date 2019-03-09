package com.shvants.runninglife;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class CounterService extends Service {

    public static final String START = "Counter service start";
    public static final String STOP = "Counter service stop";
    public static final String BIND_SERVICE = "Bind service";
    public static final String UNBIND_SERVICE = "Unbind service";

    private CounterBinder counterBinder;
    private String result;

    public CounterService() {
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Toast.makeText(getBaseContext(), START, Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getBaseContext(), STOP, Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (counterBinder == null) {
            counterBinder = new CounterBinder();
        }

        Toast.makeText(getApplicationContext(), BIND_SERVICE, Toast.LENGTH_SHORT).show();

        return counterBinder;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        Toast.makeText(getApplicationContext(), UNBIND_SERVICE, Toast.LENGTH_SHORT).show();

        return super.onUnbind(intent);
    }

    String getResult() {
        return result;
    }

    class CounterBinder extends Binder {
        CounterService getService() {
            return CounterService.this;
        }
    }
}
