package com.myapp.sshah.instr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.sshah.instr.R;
import com.myapp.sshah.instr.models.InstagramPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sshah1 on 10/11/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_feed_item, parent, false);
        }
        TextView tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        tvUsername.setText(photo.username);
        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        return convertView;
    }


}
