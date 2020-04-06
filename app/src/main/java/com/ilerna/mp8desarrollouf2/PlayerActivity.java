package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button playBtn;
    Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        playBtn = findViewById(R.id.playBtn);
        stopBtn = findViewById(R.id.stopBtn);

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
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(stopBtn);
    }
}
