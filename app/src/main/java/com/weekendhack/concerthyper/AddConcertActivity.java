package com.weekendhack.concerthyper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddConcertActivity extends AppCompatActivity {

    ArrayList<Concert> mConcerts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);


    }
}