 package com.example.soltani.scrumin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sign_up extends AppCompatActivity {
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner Drop down elements

        List<String> ROLE = new ArrayList<String>();
        ROLE.add("DEV");
        ROLE.add("Powner");
        ROLE.add("Smaster");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ROLE);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        // Spinner click listener
        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener()  {


            @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button btn=(Button)findViewById(R.id.btnins);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText n=(EditText)findViewById(R.id.name);
                EditText m=(EditText)findViewById(R.id.mail);
                EditText p=(EditText)findViewById(R.id.password);

                String nom=n.getText().toString();
                String mail=m.getText().toString();
                // a verifier .........................
                String role=spinner.getSelectedItem().toString();
              //.................................
                String pass =p.getText().toString();
                signUp(nom,mail,pass,role);
            }
        });

    }

    public void signUp(final String nom, final String mail, final String pass, final String role) {


        String URL_LOGIN="http://sney3i.tn/api/inscription";
        rq= Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"done register", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),Acceuil.class);
                finish();
                startActivity(intent);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("nom",nom);
                params.put("mail", mail);
                params.put("password", pass);
                params.put("role", role);


                return params;
            }

        };

        // Adding request to request queue
        rq.add(strReq);




    }
}
