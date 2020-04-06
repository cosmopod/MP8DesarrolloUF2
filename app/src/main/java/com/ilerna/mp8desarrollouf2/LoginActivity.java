package com.ilerna.mp8desarrollouf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String LoginBundleKey = "User";
    EditText usernameText;
    EditText passwordText;
    Button signInBtn;

    User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.loginUserNameText);
        passwordText = findViewById(R.id.loginPassText);

        signInBtn = findViewById(R.id.loginActvityBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                if (LoginUser(username, password)) LoadPlayer();
                else ShowToast();
            }
        });
    }

    private boolean LoginUser(String username, String password) {
        DatabaseManager dbManager = new DatabaseManager(this, DatabaseManager.DatabaseName, null, 1);
        User loggedUser = dbManager.GetUserByNick(username);
        if (loggedUser == null) return false;
        this.loggedUser = loggedUser;
        return loggedUser.getPassword().equals(password);
    }

    private void LoadPlayer() {
        if (loggedUser != null) {
            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra(LoginBundleKey, loggedUser);
            startActivity(intent);
        }
    }

    private void ShowToast(){
        Toast toast = Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG);
        toast.show();
    }
}
