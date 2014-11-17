package com.where2go.api.responses;

import com.where2go.api.objects.Place;

import java.util.List;

/**
 * Created by henocdz on 15/11/14.
 */
public class TreeResponse {
    public String error;
    public List<Place> results;

    public TreeResponse(String error){
        this.error = error;
    }

    public TreeResponse(List<Place> results){
        this.error = null;
        this.results = results;
    }
}
