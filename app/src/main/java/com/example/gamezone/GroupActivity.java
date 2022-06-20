package com.example.gamezone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {
    private int groupID;
    private String addUser = "";
        private String addEvent = "";
        private String addEventDate = "";
        private String addGroupID;// = String.valueOf(groupID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);
        // textViewGroupName
        //   String result = getIntent().getStringExtra("loggedUser");
        // String result2 = getIntent().getStringExtra("userName");
        // TextView userNameProfile = findViewById(R.id.userNameProfile);
        // userNameProfile.setText(userName);
        String name = getIntent().getStringExtra("groupName");
        groupID = Integer.parseInt(getIntent().getStringExtra("groupID"));
         addGroupID = String.valueOf(groupID);
        TextView grouNameTitle = findViewById(R.id.textViewGroupName);
        grouNameTitle.setText(name);

        String url = "http://192.168.43.43:8081/api/usersgroups/group?group_id=" + String.valueOf(groupID);
        Log.i("mesGetUsersFromGroup", url);


        String url2 = "http://192.168.43.43:8081/api/groupsevents/all?group_id=" + String.valueOf(groupID);
        Log.i("mesGetEventsFromGroup", url2);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, this::onResponseUsersFromGroup, this::onErrorResponseUsersFromGroup);
        StringRequest stringRequest2 = new StringRequest(com.android.volley.Request.Method.GET, url2, this::onResponseEventsFromGroup, this::onErrorResponseEventsFromGroup);
        queue.add(stringRequest);
        queue.add(stringRequest2);
    }

    private void onResponseEventsFromGroup(String response) {
        Log.i("mirekEventsGroups", "mirekEventsGroups");
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        Log.i("lista event groups", response);
        ArrayList<?> arrayEvents = gson.fromJson(response, ArrayList.class);
        ArrayList<Event> arrayEvent2 = new ArrayList<>();
        for (Object event : arrayEvents) {
            Gson gson1 = new Gson();
            JsonElement jsonElement = gson1.toJsonTree(event);
            Event event1 = gson1.fromJson(jsonElement, Event.class);
            arrayEvent2.add(event1);
        }
        Log.i("eventssssGroupsssssss", arrayEvent2.toString());

//        Tu się inicjuje adapter wyświetlający loty eventsRecycleView
        RecyclerView rvEvents = findViewById(R.id.eventsGroupsRecycleView);
        rvEvents.setNestedScrollingEnabled(false);
        EventGroupAdapter adapter = new EventGroupAdapter(arrayEvent2);

//        adapter.notifyDataSetChanged();
        rvEvents.setHasFixedSize(true);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        rvEvents.setAdapter(adapter);
    }

    private void onResponseUsersFromGroup(String response) {
    Gson gson =  new GsonBuilder().setPrettyPrinting().create();
    Log.i("lista userów", response);
    ArrayList<?> arrayUsers = gson.fromJson(response, ArrayList.class);
    ArrayList<Friend> arrayUsers2 = new ArrayList<>();
    for (Object user : arrayUsers) {
        Gson gson1 = new Gson();
        JsonElement jsonElement = gson1.toJsonTree(user);
        Friend user1 = gson1.fromJson(jsonElement, Friend.class);
        arrayUsers2.add(user1);
    }
    Log.i("userssss", arrayUsers2.toString());

//        Tu się inicjuje adapter wyświetlający loty membersRecycleView
    RecyclerView rvMembers = findViewById(R.id.membersRecycleView);
    rvMembers.setNestedScrollingEnabled(false);
    MemberAdapter adapter = new MemberAdapter(arrayUsers2);
//        adapter.notifyDataSetChanged();
    rvMembers.setHasFixedSize(true);
    rvMembers.setLayoutManager(new LinearLayoutManager(this));
    rvMembers.setAdapter(adapter);
    }

    public void onAddUserToGroupClicked (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input friend id:");
        EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addUser = input.getText().toString();
                String url = "http://192.168.43.43:8081/api/admin/group/user?user_id=" + addUser
                        + "&group_id=" + String.valueOf(groupID);
                Log.i("mesAddUserToGroup", url);

                RequestQueue queue = Volley.newRequestQueue(GroupActivity.this);
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

   public void onAddEventForGroupClicked (View view){
//        Toast.makeText(this, "CLICKED", Toast.LENGTH_LONG).show();
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       LinearLayout lila1= new LinearLayout(this);
       lila1.setOrientation(LinearLayout.VERTICAL);
       builder.setTitle("Add new event:");
       // Set up the input
       final EditText input = new EditText(this);
       // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
       // builder.setTitle("Input event date:");
       // Set up the input
       final EditText input2 = new EditText(this);
       final TextView input5 = new TextView(this);
       final TextView input6 = new TextView(this);
       input5.setText("Enter event date:");
       input6.setText("Enter event name:");
       // builder.setTitle("Input group id:");
       // Set up the input
       final EditText input3 = new EditText(this);
       lila1.addView(input6);
       lila1.addView(input); //name
       lila1.addView(input5);
       lila1.addView(input2); //date
      // lila1.addView(input3);
       builder.setView(lila1); //event name
       // builder.setView(input2); //event date
       // builder.setView(input3); //group id

       // Set up the buttons
       builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               addEvent = input.getText().toString(); //name
               addEventDate = input2.getText().toString(); //date
//                addGroupID = input3.getText().toString(); // group

               String url = "http://192.168.43.43:8081/api/admin/event?name=" + addEvent
               + "&date=" + addEventDate + "&group_id=" + addGroupID;

               Log.i("mesAddEvent", url);

               RequestQueue queue = Volley.newRequestQueue(GroupActivity.this);
               StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::onResponse, this::onErrorResponse);
               queue.add(stringRequest);
               recreate();
           }

           private void onResponse(String response) {
               Log.i("addEvent", "addEvent");
           }

           private void onErrorResponse(VolleyError error) {
               Log.i("mirekEvent", String.valueOf(error));
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


    private void onErrorResponseUsersFromGroup(VolleyError error) {
        Log.i("mirek", String.valueOf(error));
    }

    private void onErrorResponseEventsFromGroup(VolleyError error) {
        Log.i("mirekEventsGroups", String.valueOf(error));
    }

    @Override
    public void onBackPressed() {
        finish();
        recreate();
    }


}
