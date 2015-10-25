package com.nocomment.providingears;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class Audio extends Activity {


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_audio);


        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            NoiseRecorder noiseRecorder = new NoiseRecorder();
            for (int i = 0; i < 50; i++)
                Log.d("NOISE LEVEL", String.valueOf(noiseRecorder.highestNoiseLevel()));
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}