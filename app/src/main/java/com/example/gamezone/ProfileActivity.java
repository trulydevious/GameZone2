package com.example.gamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private String friend_Id = "";
    private int user;
    private String addGame = "";
    private TextView tvGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String result = getIntent().getStringExtra("loggedUser");
        user = Integer.parseInt(result);

        ImageButton button = findViewById(R.id.imageButton2);
        ImageButton button2 = findViewById(R.id.imageButtonGames);
        tvGames = findViewById(R.id.content);

        String url = "http://192.168.1.51:8081/api/usersgames/all?user_id=" + user;
        Log.i("mesGetUsersGames", url);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponseUserGames, this::onErrorResponseUserGames);
        queue.add(stringRequest);

    }

    public void onAddGamesClicked(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input game id:");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addGame = input.getText().toString();
                String url = "http://192.168.1.51:8081/api/usersgames/add?game_id=" + addGame
                        + "&user_id=" + user;
                Log.i("mesAddGame", url);

                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::onResponse, this::onErrorResponse);
                queue.add(stringRequest);
            }

            private void onResponse(String response) {
                Log.i("add", "add");
            }

            private void onErrorResponse(VolleyError error) {
                Log.i("mirek", String.valueOf(error));
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void onAddFriendClicked (View view){
        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input friend id:");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);


        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                friend_Id = input.getText().toString();
                // String url = "http://192.168.1.51:8081/api/usersgames/add?game_id=" + friend_Id
                //         + "&user_id=" + user;
                // Log.i("mesAddGame", url);

                // RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                // StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponse, this::onErrorResponse);
                // queue.add(stringRequest);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void onResponse(String response) {

    }


    private void onErrorResponse(VolleyError error) {
        Log.i("mirek", String.valueOf(error));
    }

    private void onResponseUserGames(String response) {
        Log.i("mirek", "mirek");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Game> games = gson.fromJson(response, ArrayList.class);
        Log.i("lista gier", String.valueOf(games));
//        tvGames.setText(game.getName());
    }


    private void onErrorResponseUserGames(VolleyError error) {
        Log.i("mirek", String.valueOf(error));
    }

}