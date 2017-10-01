package com.weekendhack.concerthyper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddConcertActivity extends AppCompatActivity {

    ArrayList<Concert> mConcerts = new ArrayList<>();
    SharedPreferences mPrefs;
    EditText etArtist;
    EditText etLocation;
    EditText etDate;
    EditText etTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        etArtist = (EditText) findViewById(R.id.et_manual_artist);
        etLocation = (EditText)findViewById(R.id.et_manual_location);
        etDate = (EditText)findViewById(R.id.et_manual_date);
        etTime = (EditText)findViewById(R.id.et_manual_time);

        Bundle b = getIntent().getExtras();
        String artistName = "";
        String artistId = "";
        if (b != null) {
            artistName = b.getString("Name");
            artistId = b.getString("ID");
            etArtist.setText(artistName);
        }




        Button button = (Button) findViewById(R.id.btn_add_concert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_add_concert:
                        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        final String sArtist = etArtist.getText().toString();
                        final String sLocation = etLocation.getText().toString();
                        final String sDate = etDate.getText().toString();
                        String sTime = etTime.getText().toString();

                        Log.d("ARTIST INPUT", sArtist);

                        Concert newConcert = new Concert(sArtist, sLocation, sDate);
                        //Book newbook = new Book(author, title, price, isbn, course);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        String json = gson.toJson(newConcert);

                        String key = sArtist + sDate;

                        saveChanges(mPrefs, key, json);

                        Log.w("IN CREATE", String.valueOf(mPrefs.getAll()));
                        finish();
                }
            }
        });
    }

    public void saveChanges(SharedPreferences sharedPreferences, String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
        Log.w("SAVING", "I HAVE SAVED");

    }
}
