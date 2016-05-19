package com.example.rishabh_pc.reckpi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Rishab-pc on 17-May-16.
 */
public class Login extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        EditText user = (EditText) findViewById(R.id.usnm);
        EditText pass = (EditText) findViewById(R.id.pass);
        String use = user.getText().toString();
        String pas = pass.getText().toString();
        String url = "http://192.168.137.1:8000/reckpi/default/login.json?userid="+use+"&password="+pas;

        Log.d("this url", url);
        final Intent intent = new Intent(this, MainActivity.class);
        MyJsonRequest req = new MyJsonRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONObject user = response.getJSONObject("user");
                        intent.putExtra("fullname", user.getString("fullname"));
                        startActivity(intent);
                    }
                } catch (Exception e){
                    Log.d("Login", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            if( error instanceof ParseError) {
                Log.d("response error", "error");
            }

            }
        });
        Singleton.getInstance().addToRequestQueue(req);
    }
}
