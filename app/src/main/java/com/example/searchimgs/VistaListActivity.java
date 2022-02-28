
package com.example.searchimgs;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VistaListActivity extends AppCompatActivity {
    ImageAdapter imageAdapter;
    RecyclerView recyclerView;
    String perPage = "&per_page=30&query=";
    String buscarFlores = "flower";
    String urls = "https://api.unsplash.com/search/photos/?client_id=CMuZX_EUI4rOvlERLMe3GLhFDKkNPTbKBpWJw0kKtrU&page=";
    ProgressBar progressBar;
    List<ModeloImg> listModel;
    private static final String TAG = "MainActivity";
    private float mScale = 1f;
    private ScaleGestureDetector mScaleGestureDetector;
    GestureDetector gestureDetector;
    int page = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        recyclerView = findViewById(R.id.cycle);
        progressBar = findViewById(R.id.pro);
        listModel = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, listModel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        traerImgs();
        gestureDetector = new GestureDetector(this, new GestureListener());
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;
                if (mScale > 10f)
                    mScale = 10f;
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                ScrollView layout = findViewById(R.id.scrollView);
                layout.startAnimation(scaleAnimation);
                return true;
            }
        });
    }

    private void traerImgs() {
        String getUrls = urls +page+perPage + buscarFlores;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, getUrls, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("results");
                for (int i =0;i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject getUrls1 = jsonObject.getJSONObject("urls");
                    String imgUrls = getUrls1.getString("small");
                    ModeloImg modeloImg = new ModeloImg(imgUrls);
                    listModel.add(modeloImg);
                }
                imageAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }catch (JSONException e)
            {
                e.printStackTrace();
            }

        }, error -> {

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener implements com.example.searchimgs.GestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }
}

