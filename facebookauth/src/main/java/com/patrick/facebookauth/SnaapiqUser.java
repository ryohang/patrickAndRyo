package com.patrick.facebookauth;

/**
 * Created by Pmoney on 9/25/2014.
 */
public class SnaapiqUser {

    public String id;
    public String accessToken;

    public SnaapiqUser(String id, String accessToken){
        this.id = id;
        this.accessToken = accessToken;
    }
}
