package com.example.gamezone;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText new_username;
    private EditText new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        new_username = (EditText) findViewById(R.id.new_username);
        new_password = (EditText) findViewById(R.id.new_password);

    }

    public void onButSignUpClicked(View view) {

        if (!new_username.getText().equals("") && !new_password.getText().equals("")) {
            String url = "http://192.168.1.51:8081/api/user?email=" + new_username.getText().toString() + "&password=" + new_password.getText().toString();
            Log.i("mesNewUser", url);

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::onResponse, this::onErrorResponse);
            queue.add(stringRequest);
        }
    }

    private void onResponse(String response) {

        String url = "http://192.168.1.51:8081/api/user/login?login=" + new_username.getText().toString()
                + "&password=" + new_password.getText().toString();

//        String url = "http://192.168.43.43:8081/api/user/login?login=" + new_username.getText().toString()
//                + "&password=" + new_password.getText().toString();

        Log.i("mes", url);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponse2, this::onErrorResponse2);
        queue.add(stringRequest);
    }


    private void onErrorResponse(VolleyError error) {
        Log.i("mirekRegister", String.valueOf(error));
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    private void onResponse2(String response) {

        if (Integer.parseInt(response) != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("loggedUser", response.toString());
            intent.putExtra("userName", new_username.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
        }
    }


    private void onErrorResponse2(VolleyError error) {
        Log.i("mirekRegister", String.valueOf(error));
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    public void finishRegisterActivity (View view){ finish(); }

}
