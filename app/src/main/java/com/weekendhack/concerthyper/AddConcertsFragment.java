package com.weekendhack.concerthyper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

public class AddConcertsFragment extends Fragment {

    private EditText searchInput;
    TextView mDisplay;

    public AddConcertsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_concert, container, false);
        searchInput = (EditText) view.findViewById(R.id.et_search_artist);
        mDisplay = (TextView) view.findViewById(R.id.tv_lol);
        Button button = (Button) view.findViewById(R.id.btn_manual__add_concert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_manual__add_concert:
                        Intent i = new Intent(getActivity(), AddConcertActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDisplay.setText(searchInput.getText());
                searchInput.setInputType(InputType.TYPE_CLASS_TEXT);
                String artistSearch = searchInput.getText().toString();

                //TODO: Use Spotify search
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // All your networking logic
                        // should be here
                        //URL spotifyEndpoint = new URL("https://api.spotify.com/");
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}