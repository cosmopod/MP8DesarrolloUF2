package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText nameText;
    EditText lastNameText;
    EditText emailText;
    EditText usernameText;
    EditText passwordText;

    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameText = findViewById(R.id.nameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passText);
        registerBtn = findViewById(R.id.register_activity_btn);



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(nameText.getText().toString(),
                        lastNameText.getText().toString(),
                        emailText.getText().toString(),
                        usernameText.getText().toString(),
                        passwordText.getText().toString());

                SaveUser(user);
            }
        });

    }

    private  void SaveUser(User user){

        DatabaseManager dbManager = new DatabaseManager(this, "DesarrolloUf2", null, 1);
        dbManager.InsertUser(user);
    }
}
