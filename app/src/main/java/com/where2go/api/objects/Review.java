package com.where2go.api.objects;

import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by henocdz on 17/11/14.
 */
public class Review implements Serializable {
    private String title;
    private String description;
    private float stars;
    private String image;

    public Review(float stars, String title, String description) {
        this.title = title;
        this.description = description;
        this.stars = stars;
        this.image = null;
    }

    public Review(ParseObject review){
        this.title = review.getString("title");
        this.description = review.getString("description");
        this.stars = (float) review.getDouble("stars");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
