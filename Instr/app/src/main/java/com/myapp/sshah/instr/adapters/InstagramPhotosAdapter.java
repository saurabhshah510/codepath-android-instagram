package com.myapp.sshah.instr.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.myapp.sshah.instr.models.InstagramPhoto;

import java.util.List;

/**
 * Created by sshah1 on 10/11/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }
}
