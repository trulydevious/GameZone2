package com.example.gamezone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
}

    public void onButLoginClicked (View view){

        if (username.getText().toString().equals("admin") && password.getText().toString().equals("123")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
        
    }

    public void finishLoginActivity (View view){ finish(); }


}
