package com.myapp.sshah.instr.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.myapp.sshah.instr.FeedActivity;
import com.myapp.sshah.instr.models.Comment;
import com.myapp.sshah.instr.models.InstagramPhoto;
import com.myapp.sshah.instr.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sshah1 on 10/7/15.
 */
public class InstagramClient {
    private static InstagramClient sharedInstance;
    private String CLIENT_ID = "46e4a03f28954aed8d2bc587dd698124";

    private InstagramClient(){
    }

    public static InstagramClient getSharedInstance(){
        if(sharedInstance == null){
            sharedInstance = new InstagramClient();
        }
        return sharedInstance;
    }

    public void fetchPopularPhotos(final FeedActivity activity){
        //Create network client
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        client.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray photosJSON = null;
                ArrayList<InstagramPhoto> popularPhotos = new ArrayList<>();
                try{
                    photosJSON = response.getJSONArray("data");
                    for(int i=0;i < photosJSON.length();i++){
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        User user = new User();
                        user.username = photoJSON.getJSONObject("user").getString("username");
                        user.profileImageUrl = photoJSON.getJSONObject("user").getString("profile_picture");
                        photo.user = user;
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        photo.type = photoJSON.getString("type");
                        photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likeCount = photoJSON.getJSONObject("likes").getInt("count");
                        photo.setRelativeTime(photoJSON.getString("created_time"));
                        //Get the latest 2 comments
                        JSONArray commentsJSON = photoJSON.getJSONObject("comments").getJSONArray("data");
                        photo.comments = new ArrayList<>();
                        for(int j=0;j<Math.min(1, commentsJSON.length());j++){
                            JSONObject commentJSON = commentsJSON.getJSONObject(j);
                            Comment comment = new Comment();
                            User commentUser = new User();
                            commentUser.username = commentJSON.getJSONObject("from").getString("username");
                            commentUser.profileImageUrl = commentJSON.getJSONObject("from").getString("profile_picture");
                            comment.comment = commentJSON.getString("text");
                            photo.comments.add(comment);
                        }
                        popularPhotos.add(photo);
                    }
                }catch(JSONException ex){
                    ex.printStackTrace();
                }
                activity.onSuccessPhotoFetch(popularPhotos);
                Log.i("DEBUG", response.toString());
                //Get popular photos JSON object

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
