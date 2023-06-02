package com.tmannapps.media_player;

import java.net.URL;

public class URLs {

    private int id;
    private String videoLink;


    URLs(int id, String videoLink) {
        this.id = id;
        this.videoLink = videoLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

}
