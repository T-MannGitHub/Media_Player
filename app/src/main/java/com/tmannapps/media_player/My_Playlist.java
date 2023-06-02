package com.tmannapps.media_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tmannapps.media_player.databinding.ActivityMyPlaylistBinding;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class My_Playlist extends AppCompatActivity implements RecyclerViewAdapter.OnRowClickListener {

    ActivityMyPlaylistBinding playlistBinding;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<URLs> linksList = new ArrayList<>();
    List<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playlistBinding = ActivityMyPlaylistBinding.inflate(getLayoutInflater());
        View view = playlistBinding.getRoot();
        setContentView(view);

        recyclerView = playlistBinding.recyclerView;
        recyclerViewAdapter =new RecyclerViewAdapter(linksList,  My_Playlist.this, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        urlList.add(url);


        for (int i = 0; i < urlList.size(); i++) {
            URLs urls = new URLs(i, urlList.get(i));
            linksList.add(urls);
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}