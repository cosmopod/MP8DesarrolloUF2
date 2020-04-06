package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        passwordText = findViewById(R.id.loginPassText);

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

    private void SaveUser(User user) {

        try {
            DatabaseManager dbManager = new DatabaseManager(this, DatabaseManager.DatabaseName, null, 1);
            User savedUser = dbManager.GetUserByNick(user.getUserName());
            if (savedUser == null) {
                dbManager.InsertUser(user);
                ShowToast(R.string.user_created);
                LoadLogin(user);
            } else {
                ShowToast(R.string.already_created);
            }
        } catch (Exception e) {
            ShowToast(R.string.user_error);
            System.out.println(e.getMessage());
        }
    }

    private void ShowToast(int message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void LoadLogin(User user){
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
}
