package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    //user Info
    TextView name;
    TextView lastName;
    TextView username;
    TextView email;
    TextView password;

    //Player
    MediaPlayer mediaPlayer;
    Button playBtn;
    Button stopBtn;

    User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        name = findViewById(R.id.nameLabel);
        lastName = findViewById(R.id.lastNameLabel);
        username = findViewById(R.id.userLabel);
        email = findViewById(R.id.emailLabel);
        password = findViewById(R.id.passLabel);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        playBtn = findViewById(R.id.playBtn);
        stopBtn = findViewById(R.id.stopBtn);

        RetrieveLoggedUser();
        ShowUserInfo();

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusic(v);
                int playBtnLabel = mediaPlayer.isPlaying() ? R.string.pause : R.string.play;
                playBtn.setText(playBtnLabel);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic(v);
                playBtn.setText(R.string.play);
            }
        });
    }

    private void RetrieveLoggedUser() {
        Bundle extras = getIntent().getExtras();
        loggedUser = (User) extras.getSerializable(LoginActivity.LoginBundleKey);
    }

    private void ShowUserInfo() {
        if (loggedUser == null) return;
        name.setText(loggedUser.getName());
        lastName.setText(loggedUser.getLastName());
        username.setText(loggedUser.getUserName());
        email.setText(loggedUser.getEmail());
        password.setText(loggedUser.getPassword());
    }

    private void startMusic(View v) {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    private void stopMusic(View v) {
        if (mediaPlayer == null) return;
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) mediaPlayer.stop();
    }
}
