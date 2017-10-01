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
import android.view.MotionEvent;
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
import java.util.ArrayList;
import java.util.List;

public class AddConcertsFragment extends Fragment {
    final ArrayList<Artist> artistArrayList = new ArrayList<>(5);

    private EditText searchInput;
    TextView mFirstArtist;
    TextView mSecondArtist;
    TextView mThirdArtist;
    TextView mFourthArtist;
    TextView mFifthArtist;

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
        mFirstArtist = (TextView) view.findViewById(R.id.tv_artist_1);
        mSecondArtist = (TextView) view.findViewById(R.id.tv_artist_2);
        mThirdArtist = (TextView) view.findViewById(R.id.tv_artist_3);
        mFourthArtist = (TextView) view.findViewById(R.id.tv_artist_4);
        mFifthArtist = (TextView) view.findViewById(R.id.tv_artist_5);

        mFirstArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddConcertActivity.class);
                i.putExtra("Name", artistArrayList.get(0).getName());
                i.putExtra("ID", artistArrayList.get(0).getId());
                startActivity(i);
            }
        });

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
                artistArrayList.clear();
                mFirstArtist.setText(R.string.base);
                mSecondArtist.setText(R.string.base);
                mThirdArtist.setText(R.string.base);
                mFourthArtist.setText(R.string.base);
                mFifthArtist.setText(R.string.base);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //mDisplay.setText(searchInput.getText());
                searchInput.setInputType(InputType.TYPE_CLASS_TEXT);
                final String artistSearch = searchInput.getText().toString();

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
                                    artistArrayList.add(artist);
                                    Log.i("ARTIST NAME", artist.getName());
                                }
                                mFirstArtist.setText(artistArrayList.get(0).getName());
                                mSecondArtist.setText(artistArrayList.get(1).getName());
                                mThirdArtist.setText(artistArrayList.get(2).getName());
                                mFourthArtist.setText(artistArrayList.get(3).getName());
                                mFifthArtist.setText(artistArrayList.get(4).getName());

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