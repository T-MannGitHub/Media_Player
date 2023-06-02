package com.tmannapps.media_player;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tmannapps.media_player.data.DatabaseHelper;
import com.tmannapps.media_player.databinding.ActivityMainBinding;
import com.tmannapps.media_player.databinding.ActivitySignUpBinding;
import com.tmannapps.media_player.model.User;

import java.io.File;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding signUpBinding;
    DatabaseHelper db;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    private static final int CAMERA_PERMISSION_CODE = 1;


    private Uri createUri(){
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.tmannapps.media_player.fileProvider",
                imageFile
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view =    signUpBinding.getRoot();
        setContentView(view);
        db = new DatabaseHelper(this);


        if (SignUp.this.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            //do the camera part here
            imageUri = createUri();
            registerPictureLauncher();
            signUpBinding.floatingActionButton.setOnClickListener(view2 -> {
                checkCameraPermissionAndOpenCamera();

            });
        } else {
            //have user upload from file - TODO
            Toast.makeText(SignUp.this, ("Looks like you don't have a camera"), Toast.LENGTH_SHORT).show();
        }

        signUpBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Home.class);
                startActivity(intent);
            }
        });
        signUpBinding.saveButton.setOnClickListener(v -> {
            String username = signUpBinding.sUsernameEditText.getText().toString();
            String password = signUpBinding.sPasswordEditText.getText().toString();
            String fullname = signUpBinding.fullNameEditText.getText().toString();
            String confPassword = signUpBinding.sConfirmPasswordEditText.getText().toString();

            if (password.equals(confPassword))

            {
                long result = db.insertUser(new User(username, password, fullname));
                if (result > 0)
                {
                    Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SignUp.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(SignUp.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void registerPictureLauncher() {
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if (result) {
                                signUpBinding.imageView.setImageURI(null);
                                signUpBinding.imageView.setImageURI(imageUri);
                            }
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                }
        );
    }
    private void checkCameraPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(SignUp.this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SignUp.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            takePictureLauncher.launch(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictureLauncher.launch(imageUri);

            } else {
                Toast.makeText(SignUp.this, "Camera permission denied, please allow permissions", Toast.LENGTH_SHORT).show();

            }
        }
    }




    }
