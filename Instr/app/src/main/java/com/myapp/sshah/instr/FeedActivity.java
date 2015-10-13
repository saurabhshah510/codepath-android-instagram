package com.myapp.sshah.instr;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.myapp.sshah.instr.adapters.InstagramPhotosAdapter;
import com.myapp.sshah.instr.api.InstagramClient;
import com.myapp.sshah.instr.models.InstagramPhoto;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter photosAdapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotosAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        fetchPopularPhotosAsync();
    }

    private void fetchPopularPhotosAsync(){
        photos = new ArrayList<>();
        photosAdapter = new InstagramPhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        InstagramClient.getSharedInstance().fetchPopularPhotos(this);
    }

    public void onSuccessPhotoFetch(ArrayList<InstagramPhoto> popularPhotos){
        photosAdapter.clear();
        photosAdapter.addAll(popularPhotos);
        photosAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }
}
