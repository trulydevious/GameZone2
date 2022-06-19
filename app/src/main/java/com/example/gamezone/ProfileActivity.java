package com.example.gamezone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements DeleteBtnClicked {
    private String friend_Id = "";
    private int user;
    private String addGame = "";
    private String addFriend = "";
    private TextView tvGames;
    private DeleteBtnClicked deleteBtnClicked = this;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.i("start", "start");

        String result = getIntent().getStringExtra("loggedUser");
        String result2 = getIntent().getStringExtra("userName");
        userName = result2;
        user = Integer.parseInt(result);

        ImageButton button = findViewById(R.id.imageButton2);
        ImageButton button2 = findViewById(R.id.imageButtonGames);
        tvGames = findViewById(R.id.content);
        ImageButton button3 = findViewById(R.id.deleteBtnGame);
        TextView userNameProfile = findViewById(R.id.userNameProfile);
        userNameProfile.setText(userName);

        String url = "http://192.168.1.51:8081/api/usersgames/all?user_id=" + user;
        Log.i("mesGetUsersGames", url);

        String url2 = "http://192.168.1.51:8081/api/usersusers/all?user_id=" + user;
        Log.i("mesGetUsersFriends", url2);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponseUserGames, this::onErrorResponseUserGames);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, this::onResponseFriends, this::onErrorResponseFriends);

        queue.add(stringRequest);
        queue.add(stringRequest2);

    }

    @Override
    public void deleteBtnClick() {
        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
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
                recreate();
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

    public void refreshClicked (View view){
        recreate();
    }

    public void onAddFriendClicked (View view){
//        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
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
                addFriend = input.getText().toString();
                String url = "http://192.168.1.51:8081/api/usersusers/add?friend_id=" + addFriend
                        + "&user_id=" + user;
                Log.i("mesAddFriend", url);

                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::onResponse, this::onErrorResponse);
                queue.add(stringRequest);
                recreate();
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

    private void onResponseFriends(String response) {
        Log.i("mirek2", "mirek2");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        Log.i("lista ludzi", response);
        ArrayList<?> arrayFriends = gson.fromJson(response, ArrayList.class);
        ArrayList<Friend> arrayFriends2 = new ArrayList<>();
        for (Object friend : arrayFriends) {
            Gson gson1 = new Gson();
            JsonElement jsonElement = gson1.toJsonTree(friend);
            Friend friend1 = gson1.fromJson(jsonElement, Friend.class);
            arrayFriends2.add(friend1);
        }
        Log.i("friendssss", arrayFriends2.toString());

//        Tu się inicjuje adapter wyświetlający loty
        RecyclerView rvFriends = findViewById(R.id.friendsRecycleView);
        rvFriends.setNestedScrollingEnabled(false);
        FriendsAdapter adapter = new FriendsAdapter(arrayFriends2, user, deleteBtnClicked);
//        adapter.notifyDataSetChanged();
        rvFriends.setHasFixedSize(true);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        rvFriends.setAdapter(adapter);
    }


    private void onErrorResponseFriends(VolleyError error) {
        Log.i("mirekFriends", String.valueOf(error));
    }

    private void onResponseUserGames(String response) {
        Log.i("mirek", "mirek");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        Log.i("lista gier", response);
        ArrayList<?> arrayGames = gson.fromJson(response, ArrayList.class);
        ArrayList<Game> arrayGames2 = new ArrayList<>();
        for (Object game : arrayGames) {
            Gson gson1 = new Gson();
            JsonElement jsonElement = gson1.toJsonTree(game);
            Game game1 = gson1.fromJson(jsonElement, Game.class);
            arrayGames2.add(game1);
        }
        Log.i("gamessss", arrayGames2.toString());
        
//        Tu się inicjuje adapter wyświetlający loty
        RecyclerView rvGames = findViewById(R.id.gamesRecycleView);
        rvGames.setNestedScrollingEnabled(false);
        GameAdapter adapter = new GameAdapter(arrayGames2, user, deleteBtnClicked);
//        adapter.notifyDataSetChanged();
        rvGames.setHasFixedSize(true);
        rvGames.setLayoutManager(new LinearLayoutManager(this));
        rvGames.setAdapter(adapter);


    }


    private void onErrorResponseUserGames(VolleyError error) {
        Log.i("mirek", String.valueOf(error));
    }

}