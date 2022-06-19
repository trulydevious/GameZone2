package com.example.gamezone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DeleteBtnClicked {

    private int user;
    private String userName;
    private DeleteBtnClicked deleteBtnClicked = this;
    private String addGroup = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Switch simpleSwitch = findViewById(R.id.switch4); // initiate Switch
        simpleSwitch.setTextOff("off");
        simpleSwitch.setTextOn("on");
        String result = getIntent().getStringExtra("loggedUser");
        String result2 = getIntent().getStringExtra("userName");
        user = Integer.parseInt(result);
        userName = result2;
        Log.i("user", String.valueOf(user));

//        ImageButton addEventButton = findViewById(R.id.addEventButton);
        ImageButton addGroupButton = findViewById(R.id.addGroupButton);

        //grupy - wszystkie dla danego usera
        String url = "http://192.168.1.51:8081/api/usersgroups/all?user_id=" + user;
        Log.i("mesGetUsersGroups", url);

        //eventy -  wszystkie dla danego usera /usersevents/all
        String url2 = "http://192.168.1.51:8081/api/usersevents/all?user_id=" + user;
        Log.i("mesGetUsersEvents", url2);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponseUserGroups, this::onErrorResponseUserGroups);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, this::onResponseEvents, this::onErrorResponseEvents);

        queue.add(stringRequest);
        queue.add(stringRequest2);

    }

    public void onProfileClicked (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("loggedUser", String.valueOf(user));
        intent.putExtra("userName", userName);
        startActivity(intent);
//
    }

    @Override
    public void deleteBtnClick() {
        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
    }

    public void finishMainActivity (View view){ finish(); }


    public void onAddGroupClicked(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input group id:");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addGroup = input.getText().toString();
                String url = "http://192.168.1.51:8081/api/admin/group/user?user_id=" + user
                        + "&group_id=" + addGroup;
                Log.i("mesAddGame", url);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::onResponse, this::onErrorResponse);
                queue.add(stringRequest);
                recreate();
            }

            private void onResponse(String response) {
                Log.i("addGroup", "addGroup");
            }

            private void onErrorResponse(VolleyError error) {
                Log.i("mirekGroup", String.valueOf(error));
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



    private void onResponseEvents(String response) {
        Log.i("mirekEvents", "mirekEvents");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        Log.i("lista event", response);
        ArrayList<?> arrayEvents = gson.fromJson(response, ArrayList.class);
        ArrayList<Event> arrayEvent2 = new ArrayList<>();
        for (Object event : arrayEvents) {
            Gson gson1 = new Gson();
            JsonElement jsonElement = gson1.toJsonTree(event);
            Event event1 = gson1.fromJson(jsonElement, Event.class);
            arrayEvent2.add(event1);
        }
        Log.i("eventssss", arrayEvent2.toString());

//        Tu się inicjuje adapter wyświetlający loty
        RecyclerView rvEvents = findViewById(R.id.eventsRecycleView);
        rvEvents.setNestedScrollingEnabled(false);
        EventAdapter adapter = new EventAdapter(arrayEvent2, user, deleteBtnClicked);

//        adapter.notifyDataSetChanged();
        rvEvents.setHasFixedSize(true);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        rvEvents.setAdapter(adapter);
    }


    private void onErrorResponseEvents(VolleyError error) {
        Log.i("mirekFriends", String.valueOf(error));
    }

    private void onResponseUserGroups(String response) {
        Log.i("mirekGroups", "mirekGroups");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        Log.i("lista grup", response);
        ArrayList<?> arrayGroups = gson.fromJson(response, ArrayList.class);
        ArrayList<Group> arrayGroups2 = new ArrayList<>();
        for (Object group : arrayGroups) {
            Gson gson1 = new Gson();
            JsonElement jsonElement = gson1.toJsonTree(group);
            Group group1 = gson1.fromJson(jsonElement, Group.class);
            arrayGroups2.add(group1);
        }
        Log.i("groupssss", arrayGroups2.toString());

//        Tu się inicjuje adapter wyświetlający loty
        RecyclerView rvGroups = findViewById(R.id.groupsRecycleView);
        rvGroups.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(arrayGroups2, user, deleteBtnClicked);
//        adapter.notifyDataSetChanged();
        rvGroups.setHasFixedSize(true);
        rvGroups.setLayoutManager(new LinearLayoutManager(this));
        rvGroups.setAdapter(adapter);


    }
    
    public void refreshClicked (View view){
        recreate();
    }

    private void onErrorResponseUserGroups(VolleyError error) {
        Log.i("mirekGroups", String.valueOf(error));
    }

}
