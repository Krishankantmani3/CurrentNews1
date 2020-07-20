package com.example.android.currentnews;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;


public class TechnologyFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleAdapter myAdapter;
    private ProgressDialog progressDialog;
    private static final String TAG =EntertainmentFragment.class.getSimpleName();
    private static final String URL="http://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=cbd46bd6a4f54fe69d0cb261dbe1a878\n";

    public TechnologyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview= inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerView=rootview.findViewById(R.id.headRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        progressDialog=new ProgressDialog(getContext(),R.style.ProgressColor);
        progressDialog.setMessage("loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        jsonObjectReq();
        return rootview;
    }

    private void jsonObjectReq(){
        // Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String result = "Your IP Address is " + response.getString("status");
                            Log.i("respose : ", response.toString());
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            Model model = gson.fromJson(String.valueOf(response), Model.class);
                            Article articles[] = model.getArticles().toArray(new Article[0]);
                            progressDialog.dismiss();
                            myAdapter = new ArticleAdapter(getContext(), articles);
                            recyclerView.setAdapter(myAdapter);
                            myAdapter.notifyDataSetChanged();

                            // Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                VolleyLog.e("Error: ", error.getMessage());
                Log.i("Error " + ": ", "Error : " + error.getMessage());
                if (null != error.networkResponse) {
                    Log.i("Error " + ": ", "Error Response code: " + error.networkResponse.statusCode);
                }
            }
        });
        jsonObjectRequest.setTag(TAG);
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

    }



    @Override
    public void onStop() {
        super.onStop();
        MySingleton.getInstance(getContext()).cancelToRequestQueue(TAG);
    }

    final GestureDetector gesture=new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            progressDialog.show();
            jsonObjectReq();
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    });

}
