package com.tmannapps.media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tmannapps.media_player.data.DatabaseHelper;
import com.tmannapps.media_player.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //icon <a href="https://www.flaticon.com/free-icons/music-video" title="music video icons">Music video icons created by Freepik - Flaticon</a>
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DatabaseHelper db;
        db = new DatabaseHelper(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_CAMERA_BUTTON);

        binding.buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = db.fetchUser(binding.editTextUsername.getText().toString(), binding.editTextPassword.getText().toString());
                if (result) {
                    Toast.makeText(MainActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "The username and password do not match. Please try again or create an account", Toast.LENGTH_SHORT).show();
                }
            }
            });

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(signupIntent);
            }
        });
        }

    }
