package com.example.gamezone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    public void onProfileClicked (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

    public void finishMainActivity (View view){ finish(); }

}
