package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.sign_in_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLogIn(v);
            }
        });
    }


    private void loadLogIn(View v){
        Toast toast = Toast.makeText(getApplicationContext(), "login", Toast.LENGTH_SHORT);
        toast.show();
    }
}
