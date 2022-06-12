package com.example.gamezone;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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


            String url = "http://192.168.1.51:8081/api/user/login?login=" + username.getText().toString()
                            + "&password=" + password.getText().toString();

//        String url = "http://192.168.43.43:8081/api/user/login?login=" + username.getText().toString()
//                + "&password=" + password.getText().toString();

            Log.i("mes", url);

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponse, this::onErrorResponse);
            queue.add(stringRequest);
    }



    private void onResponse(String response) {

        if (Integer.parseInt(response) != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("loggedUser", response.toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
        }
    }


    private void onErrorResponse(VolleyError error) {
        Log.i("mirek", String.valueOf(error));
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    public void finishLoginActivity (View view){ finish(); }



}
