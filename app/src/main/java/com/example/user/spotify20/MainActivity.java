package com.example.user.spotify20;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IRecycleViewSelectedElement {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Song> songsList;

    Context mContext;
    Intent songStreamService;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        songsList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTask1);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewAdapter(songsList, this);
        mRecyclerView.setAdapter(mAdapter);

        createSongs();



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(songStreamService);
                fab.setVisibility(View.INVISIBLE);
            }
        });
    }

    // Create songs for test
    private void createSongs(){
        Song song = new Song(1, "Popcorn", "Dean Cohen", "popcorn");
        songsList.add(song);

        song = new Song(2, "Surface", "Aero Chord", "surface");
        songsList.add(song);

        song = new Song(3, "Boundless", "Aero Chord", "boundless");
        songsList.add(song);

        song = new Song(4, "Freaks", "Timmy Trumpet", "freaks");
        songsList.add(song);

        song = new Song(5, "Dum Dee Dum", "Keys N Krates", "dum_dee_dum");
        songsList.add(song);

        song = new Song(6, "Atom Bomb", "Benasis", "atom_bomb");
        songsList.add(song);

        song = new Song(7, "Saiko", "Aero Chord", "saiko");
        songsList.add(song);

        song = new Song(8, "Turn down for what", "DJ Snake & Lil Jon", "turn_down_for_what");
        songsList.add(song);

        song = new Song(9, "Smile", "Joker Inc", "smile");
        songsList.add(song);

        song = new Song(10, "Bird Machine", "DJ Snake", "bird_machine");
        songsList.add(song);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(int position) {

        // Check if service is running to avoid problems with clicking on other song
        if(songStreamService != null){
            stopService(songStreamService);
        }


        fab.setVisibility(View.VISIBLE);

        String songDirectory = songsList.get(position).getSongDirectory();

        songStreamService = new Intent(this, SongStreamService.class);
        songStreamService.putExtra("MP3 file of song", songDirectory);

        startService(songStreamService);
    }

}