package com.example.ghanshyam.mongorest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final static String connectionString = "mongodb://gghanshyam01:9673672849@ds123799.mlab.com:23799/trail-db";
    EditText txtName, txtAge;
    HashMap<String, String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        map = new HashMap<String, String>();
    }

    public void onLoginClick(View view) {
        map.put("name", txtName.getText().toString());
        map.put("age", txtAge.getText().toString());
        map.put("contactNo", "This is a test contact no.");
        final String apiKey = "5LS_CdFDp4qCkidKgHaUBWCPtBPf3cZW";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringBuilder baseUrl = new StringBuilder("https://api.mlab.com/api/1/databases/trail-db/collections/users?apiKey=");
        baseUrl.append(apiKey);

//        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, baseUrl.toString(), null,
//                new Response.Listener<JSONArray>()
//                {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            Log.d("Response: ", response.toString());
//                            StringBuilder names = new StringBuilder();
//                            names.append(((JSONObject)response.get(0)).getString("name"));
//                            names.append('\n');
//                            names.append(((JSONObject)response.get(1)).getString("name"));
//                            Toast.makeText(MainActivity.this, names.toString(), Toast.LENGTH_LONG).show();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("ErrorResponse: ", error.toString());
//                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }
//        );

        JsonObjectRequest postJson = new JsonObjectRequest(Request.Method.POST, baseUrl.toString(), new JSONObject(map),
                (JSONObject response) -> {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                    Log.d("Success Response: ", response.toString());
                },
                (VolleyError error) -> {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    Log.d("Error Response: ", error.toString());
                }
        );
//        };

//        queue.add(getRequest);
        queue.add(postJson);


    }
}

