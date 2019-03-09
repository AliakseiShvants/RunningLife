package com.shvants.runninglife;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static final String RESULT = "RESULT";
    public static final int ONE_THOUSAND = 1000;

    private Intent serviceIntent;
    private CounterService counterService;
    private String result;
    private CounterServiceConnection connection = new CounterServiceConnection();

    private IntentFilter intentFilter = new IntentFilter(Const.ACTION);
    private CounterBroadcastReceiver counterBroadcastReceiver = new CounterBroadcastReceiver();

    private long start;
    private long stop;

    private static String getCounterValue(final long stop, final long start) {
        return String.valueOf((stop - start) / ONE_THOUSAND);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.main_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.training_detail_fragment, new TrainingDetailFragment())
                        .commit();
            }
        });

        serviceIntent = new Intent(this, CounterService.class);

        findViewById(R.id.main_start_service_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                start = System.currentTimeMillis();
                startService(serviceIntent);
                bindService(serviceIntent, connection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.main_stop_service_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stop = System.currentTimeMillis();
                unbindService(connection);
                stopService(serviceIntent);
            }
        });

        findViewById(R.id.main_broadcast_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent broadcastIntent = new Intent(Const.ACTION);
                broadcastIntent.putExtra(RESULT, getCounterValue(stop, start));
                sendBroadcast(broadcastIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(counterBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(counterBroadcastReceiver);
    }

    public class CounterServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            if (service instanceof CounterService.CounterBinder) {
                counterService = ((CounterService.CounterBinder) service).getService();
                result = counterService.getResult();
            }
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            counterService = null;
        }
    }
}
