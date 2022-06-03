package com.example.gamezone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

//        //pobranie grup
//        String url = "http://192.168.1.51:8081/api/user/login?login=";
//        Log.i("mes", url);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponse, this::onErrorResponse);
//        queue.add(stringRequest);

    }

    public void onProfileClicked (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("loggedUser", String.valueOf(user));
        startActivity(intent);

    }

    public void finishMainActivity (View view){ finish(); }


//    private void onResponse(String response) {
//
//        if (Integer.parseInt(response) != 0) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("loggedUser", response.toString());
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    private void onErrorResponse(VolleyError error) {
//        Log.i("mirek", String.valueOf(error));
//    }

}
