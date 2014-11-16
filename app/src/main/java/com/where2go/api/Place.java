package com.where2go.api;

import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by henocdz on 13/11/14.
 */
public class Place implements Serializable {

    protected String name;
    public Place(ParseObject place){
        name = place.getString("name");
    }

    public String getName(){
        return this.name;
    }
}
