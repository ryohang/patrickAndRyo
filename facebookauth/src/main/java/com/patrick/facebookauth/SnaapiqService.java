package com.patrick.facebookauth;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Pmoney on 9/25/2014.
 */
public interface SnaapiqService {

    @POST("/user/token")
    void cacheToken(@Body SnaapiqUser user);

    @GET("/user/token")
    String retrieveToken(@Body SnaapiqUser user);
}
