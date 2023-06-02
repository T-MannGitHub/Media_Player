package com.tmannapps.media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.tmannapps.media_player.databinding.ActivityVideoPlayerBinding;

import java.io.IOException;

public class Video_Player extends AppCompatActivity {

    ActivityVideoPlayerBinding videoPlayerBinding;
    //public static final String MEDIA_NAME = "lost_found_demo";
    VideoView videoView;


/*    private Uri getMedia (String mediaName)
    {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + mediaName);
        //return Uri.parse(link);
    }*/

    private void initialisePlayer()
    {
        //Uri videoUri = getMedia(MEDIA_NAME);
        //videoView.setVideoURI(videoUri);
        //videoView.start();
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        Toast.makeText(this, "link text = " + link , Toast.LENGTH_SHORT).show();
        Uri playMe = Uri.parse(link);
        videoView.setVideoURI(playMe);
        videoView.start();


    }

    private void releasePlayer()
    {
        videoView.stopPlayback();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoPlayerBinding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        View view = videoPlayerBinding.getRoot();
        setContentView(view);

        videoView = videoPlayerBinding.videoView;
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialisePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialisePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        {
            videoView.pause();
        }
    }
}