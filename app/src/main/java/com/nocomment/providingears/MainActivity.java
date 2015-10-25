package com.nocomment.providingears;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#54DEFD")));
        Spannable text = new SpannableString("TEST FONT");
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, "test".length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getActionBar().setTitle(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAudioClick(View view){
        startActivity(new Intent(getApplicationContext(), Audio.class));
    }

    public void onTranslateClick(View view){
        startActivity(new Intent(getApplicationContext(), Translate.class));
    }

    public void onWeatherClick(View view){
        startActivity(new Intent(getApplicationContext(), Weather.class));
    }
}
