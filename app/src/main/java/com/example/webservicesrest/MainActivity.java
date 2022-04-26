package com.example.webservicesrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUserId, txtBody, txtTitle;
    Button btnEnviar;
    Button btnleer;
    Button btneli;
    Button btnActu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserId = findViewById(R.id.txtUserId);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnleer = findViewById(R.id.btnleer);
        btneli = findViewById(R.id.btneli);
        btnActu = findViewById(R.id.btnActu);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //LeerWs(txtUserId.getText().toString());
                EnviarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //LeerWs();
                //ActualizaWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //EliminarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
            }
        });

        btnleer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWs(txtUserId.getText().toString());
                //EnviarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //LeerWs();
                //ActualizaWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //EliminarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
            }
        });

        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LeerWs(txtUserId.getText().toString());
                //EnviarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //LeerWs();
                //ActualizaWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                EliminarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
            }
        });

        btnActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LeerWs(txtUserId.getText().toString());
                //EnviarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //LeerWs();
                ActualizaWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
                //EliminarWs(txtTitle.getText().toString(), txtBody.getText().toString(), txtUserId.getText().toString());
            }
        });
    }

    private void LeerWs(String name){
        String url = "http://10.0.2.2:3000/posts/";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url + name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtUserId.setText(jsonObject.getString("id"));
                    txtTitle.setText(jsonObject.getString("name"));
                    txtBody.setText(jsonObject.getString("Asunto"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void EnviarWs(String name, String Asunto, String id){
        String url = "http://10.0.2.2:3000/posts";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    /*txtUserId.setText(jsonObject.getString("userId"));
                    txtTitle.setText(jsonObject.getString("title"));
                    txtBody.setText(jsonObject.getString("body"));*/
                    Toast.makeText(MainActivity.this, "RESULTADO = " + response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        })
        {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("Asunto", Asunto);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void ActualizaWs(final String name, final String Asunto, final String id){
        String url = "http://10.0.2.2:3000/Pacientes/";
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                   /* txtUserId.setText(jsonObject.getString("id"));
                    txtTitle.setText(jsonObject.getString("name"));
                    txtBody.setText(jsonObject.getString("Asunto"));*/
                    Toast.makeText(MainActivity.this, "RESULTADO = " + response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        })
        {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("Asunto", Asunto);
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }
    private void EliminarWs(String name, String Asunto, String id){
        String url = "http://10.0.2.2:3000/Pacientes/";
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    /*txtUserId.setText(jsonObject.getString("userId"));
                    txtTitle.setText(jsonObject.getString("title"));
                    txtBody.setText(jsonObject.getString("body"));*/
                    Toast.makeText(MainActivity.this, "RESULTADO = " + response, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(postRequest);
    }
}