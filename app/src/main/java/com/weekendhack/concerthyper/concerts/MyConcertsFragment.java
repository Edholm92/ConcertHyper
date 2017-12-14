package com.weekendhack.concerthyper.concerts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weekendhack.concerthyper.calendar.CalendarAdapter;
import com.weekendhack.concerthyper.R;

import java.util.ArrayList;
import java.util.Map;

public class MyConcertsFragment extends Fragment {

    SharedPreferences mPrefs;
    private ListView saveListView;
    public ArrayList<String> listOfConcerts;
    //String json;

    public MyConcertsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MYCONCERTS", "FRAGMENT");
        View rootView = inflater.inflate(R.layout.fragment_my_concerts, container, false);
        saveListView = (ListView) rootView.findViewById(R.id.lv_my_concerts);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        Map<String, ?> map = mPrefs.getAll();

        listOfConcerts = new ArrayList();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            listOfConcerts.add(entry.getKey());
        }

        CalendarAdapter adapter = new CalendarAdapter(getContext(), listOfConcerts);
        saveListView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }
}
