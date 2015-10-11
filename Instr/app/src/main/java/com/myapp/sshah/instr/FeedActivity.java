package com.myapp.sshah.instr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.myapp.sshah.instr.adapters.InstagramPhotosAdapter;
import com.myapp.sshah.instr.api.InstagramClient;
import com.myapp.sshah.instr.models.InstagramPhoto;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        photos = new ArrayList<>();
        photosAdapter = new InstagramPhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        InstagramClient.getSharedInstance().fetchPopularPhotos(photosAdapter);
    }
}
