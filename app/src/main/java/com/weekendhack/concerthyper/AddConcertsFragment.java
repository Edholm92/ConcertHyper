package com.weekendhack.concerthyper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;

import java.net.URL;
import java.util.List;

public class AddConcertsFragment extends Fragment {

    private EditText searchInput;
    TextView mDisplay;

    // Create an API instance. The default instance connects to https://api.spotify.com/.
    Api api = MainActivity.API;

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

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //mDisplay.setText(searchInput.getText());
                searchInput.setInputType(InputType.TYPE_CLASS_TEXT);
                final String artistSearch = searchInput.getText().toString();

                //TODO: Use Spotify search
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            //Your code goes here
                            final ArtistSearchRequest request = api.searchArtists(artistSearch).market("SE").limit(5).build();

                            try {
                                final Page<Artist> artistSearchResult = request.get();
                                final List<Artist> artists = artistSearchResult.getItems();

                                Log.i("FOUND","I've found " + artistSearchResult.getTotal() + " artists!");

                                for (Artist artist : artists) {
                                    mDisplay.setText(artist.getName());
                                    Log.i("ARTIST NAME", artist.getName());
                                }

                            } catch (Exception e) {
                                Log.w("ISSUE","Something went wrong!" + e.getMessage() + e.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });

    }

}