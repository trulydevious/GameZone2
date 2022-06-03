package com.example.gamezone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Switch simpleSwitch = findViewById(R.id.switch4); // initiate Switch
        simpleSwitch.setTextOff("off");
        simpleSwitch.setTextOn("on");
        String result = getIntent().getStringExtra("loggedUser");
        user = Integer.parseInt(result);
        Log.i("user", String.valueOf(user));

    }

    public void onProfileClicked (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

    public void finishMainActivity (View view){ finish(); }

}
