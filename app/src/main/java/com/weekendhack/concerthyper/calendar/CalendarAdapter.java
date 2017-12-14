package com.weekendhack.concerthyper.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weekendhack.concerthyper.R;

import java.util.ArrayList;

/**
 * Created by hedholm on 2017-10-01.
 */

public class CalendarAdapter extends ArrayAdapter<String> {

    public CalendarAdapter(Context context, ArrayList<String> concerts) {
        super(context, 0, concerts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String concert = getItem(position);

        String json = prefs.getString(concert,"artist");
        //String location = prefs.getString(concert,"location");

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (new JsonParser()).parse(json).getAsJsonObject();

        String artist = jsonObject.get("artist").toString();
        artist = artist.substring(1, artist.length()-1);
        String location = jsonObject.get("location").toString();
        location = location.substring(1, location.length()-1);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_calendar, parent, false);
        }

        TextView tvCalendarArtist = (TextView) convertView.findViewById(R.id.tv_calendar_artist);
        TextView tvCalendarLocation = (TextView) convertView.findViewById(R.id.tv_calendar_location);
        tvCalendarArtist.setText(artist);
        tvCalendarLocation.setText(location);

        return convertView;
    }
}
