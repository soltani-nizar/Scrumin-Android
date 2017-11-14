package com.example.soltani.scrumin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Sign_in extends AppCompatActivity {
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        Button btn = (Button) findViewById(R.id.btnCnx);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText l = (EditText) findViewById(R.id.signInMail);
                EditText m = (EditText) findViewById(R.id.signInMdp);
                String email = l.getText().toString();
                String mdp = m.getText().toString();
                checkLogin(email, mdp);


            }
        });
    }
    public void checkLogin(final String email, final String mdp){
        String URL="http://sney3i.tn/api/login";
        rq= Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jObj = new JSONObject(response);
                    String resultat = jObj.getString("resultat");

                    if(resultat.equals("false")){
                        Toast.makeText(getApplicationContext(),email+" "+mdp,Toast.LENGTH_LONG).show();
                    }else {
                        Intent i=new Intent(getApplicationContext(),Acceuil.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();

                    }







                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"erreur"+error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",mdp);



                return params;
            }

        };

        // Adding request to request queue
        rq.add(strReq);



    }
}
