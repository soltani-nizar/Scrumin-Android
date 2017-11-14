package com.example.soltani.scrumin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Acceuil extends AppCompatActivity {
    RequestQueue rq; String app_id;
    ListView lv;    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Chargement ...");
        progressDialog.show();
        int id=1;//

        lv = (ListView) findViewById(R.id.lvf);
        GetProjet(id);


        Toolbar  mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void GetProjet(final int id) {
        String URL="http://sney3i.tn/api/profile";
        rq= Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();

                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONObject data = jObj.getJSONObject("data");






                    final ArrayList<ListProject> arrayList=new ArrayList<>();
                    JSONArray avis = data.getJSONArray("avis");
                    int fin=avis.length();
                    for(int i=fin-1; i>0; i--) {
                        JSONObject avisObj = avis.getJSONObject(i);
                        String app_name=avisObj.getString("app_name");
                        app_id=avisObj.getString("app_id");

                        arrayList.add(new ListProject(app_name,app_id));
                    }

                    ProjectAdapter myadapter=new ProjectAdapter(arrayList);

                    lv.setAdapter(myadapter);

                    progressDialog.dismiss();

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                            intent.putExtra("id",arrayList.get(i).app_id);
                            startActivity(intent);
                        }
                    });





                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(Acceuil.this)
                                    .setTitle("Attention")
                                    .setMessage("Mobile data turned off.Connect to Wi-Fi network instead or turn on mobile data and try again.")
                                    .setCancelable(false)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).show();
                        }
                    }
                });

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id+"");



                return params;
            }

        };

        // Adding request to request queue
        rq.add(strReq);

    }




}


