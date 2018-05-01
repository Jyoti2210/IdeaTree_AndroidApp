package mj.jyoti.com.groovewithmj;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
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
import java.util.List;

///////////////////////////////////////////////////////////////////////////////////
/////                      Created by Jyoti Bansal                          ///////
/////                                                                       ///////
/////        This class creates an adapter for the recyclerview to parse    ///////
/////         the JSON data from the API given and display the results      ///////
/////                                                                       ///////
/////                                                                       ///////
///////////////////////////////////////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity{

    private static final String URL_DATA = "https://itunes.apple.com/search?term=Michael+jackson";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<musicData> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //adapter = new RecyclerviewAdapter(new ArrayList<musicData>(),getApplicationContext());
        //recyclerView.setAdapter(adapter);

        data = new ArrayList<>();

        loadURLData();
    }

    private void loadURLData(){
        /*final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                        musicData data1 = new musicData(jo.getString("artworkUrl100"),jo.getString("trackName"),jo.getString("artistName"),jo.getString("trackPrice"));
                        data.add(data1);

                    }
                    adapter = new RecyclerviewAdapter(data,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
