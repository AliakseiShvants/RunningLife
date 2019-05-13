package com.shvants.runninglife.loaders;

import android.os.AsyncTask;

public class JavaAsync extends AsyncTask<Void, Void, String> {


    @Override
    protected String doInBackground(final Void... voids) {
        System.out.println("inside doinback");
        return "42";
    }

    @Override
    protected void onPostExecute(final String s) {
        System.out.println("our result: " + s);

    }
}
