package com.tmannapps.media_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.tmannapps.media_player.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity {
    ActivityHomeBinding homeBinding;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view =    homeBinding.getRoot();
        setContentView(view);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.aladdin_one_jump);
        homeBinding.buttonPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });
        homeBinding.buttonPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Home.this, Video_Player.class);
                intent.putExtra("link", homeBinding.editTextURL.getText().toString());
                startActivity(intent);
            }
        });
        homeBinding.buttonAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = homeBinding.editTextURL.getText().toString();
                Intent intent = new Intent (Home.this, My_Playlist.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });
    homeBinding.buttonMyPlaylist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (Home.this, My_Playlist.class);
            startActivity(intent);
        }
    });

    }
    public void playMusic(){
        if (mediaPlayer != null) {mediaPlayer.start();
        homeBinding.buttonPlayMusic.setText("PAUSE MUSIC");}
    }
    public void pauseMusic(){
        if (mediaPlayer != null) {mediaPlayer.pause();
            homeBinding.buttonPlayMusic.setText("PLAY MUSIC");}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
        }

    }


}
