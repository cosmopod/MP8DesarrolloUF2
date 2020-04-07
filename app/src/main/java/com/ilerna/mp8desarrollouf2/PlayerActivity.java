package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends AppCompatActivity {

    //user Info
    TextView name;
    TextView lastName;
    TextView username;
    TextView email;
    TextView password;

    //Player
    ProgressBar progressBar;
    MediaPlayer mediaPlayer;
    Button playBtn;
    Button stopBtn;
    Timer playerTimer;

    User loggedUser;

    boolean IsPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        name = findViewById(R.id.nameLabel);
        lastName = findViewById(R.id.lastNameLabel);
        username = findViewById(R.id.userLabel);
        email = findViewById(R.id.emailLabel);
        password = findViewById(R.id.passLabel);

        progressBar = findViewById(R.id.progressBar);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);


        playBtn = findViewById(R.id.playBtn);
        playBtn.setEnabled(false);
        stopBtn = findViewById(R.id.stopBtn);

        RetrieveLoggedUser();
        ShowUserInfo();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playBtn.setEnabled(true);
            }
        });

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
        if (IsPlaying) {
            IsPlaying = false;
            mediaPlayer.pause();
        } else {
            IsPlaying = true;
            mediaPlayer.start();
        }
    }

    private void stopMusic(View v) {
        if (mediaPlayer == null) return;
        IsPlaying = false;
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        progressBar.setProgress(0);
    }

    private void setProgressBar() {
        playerTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (IsPlaying) {
                    float currentPosition = mediaPlayer.getCurrentPosition();
                    float duration = mediaPlayer.getDuration();
                    float progress = (currentPosition / duration) * 100;
                    progressBar.setProgress((int) progress);
                }
            }
        };

        playerTimer.schedule(timerTask, 0, 100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setProgressBar();
        if (!IsPlaying) {
            playBtn.setText(R.string.play);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerTimer.cancel();
        playerTimer.purge();
        if (mediaPlayer != null && IsPlaying) {
            IsPlaying = false;
            mediaPlayer.pause();
        }
    }
}
