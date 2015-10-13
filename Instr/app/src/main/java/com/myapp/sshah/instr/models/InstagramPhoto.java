package com.myapp.sshah.instr.models;

import java.util.ArrayList;

/**
 * Created by sshah1 on 10/7/15.
 */
public class InstagramPhoto {
    public String caption;
    public String type;
    public String imageUrl;
    public int imageHeight;
    public int likeCount;
    public String relativeTime;
    public User user;
    public ArrayList<Comment> comments;

    public void setRelativeTime(String createdAt){
        long difference = System.currentTimeMillis()/1000 - Long.parseLong(createdAt);
        StringBuilder relTime = new StringBuilder("");
        long days = difference/86400;
        if(days > 0){
            relTime.append(days);
            relTime.append(" days ago");
        }else{
            long hours = difference/3600;
            if(hours > 0){
                relTime.append(hours);
                if(hours == 1){
                    relTime.append(" hour ago");
                }else{
                    relTime.append(" hours ago");
                }

            }else{
                long minutes = difference/60;
                if(minutes > 0){
                    relTime.append(minutes);
                    if(minutes == 1){
                        relTime.append(" minute ago");
                    }else{
                        relTime.append(" minutes ago");
                    }
                }else{
                    relTime.append(difference);
                    relTime.append(" seconds ago");
                }
            }
        }
        this.relativeTime = relTime.toString();
    }
}
