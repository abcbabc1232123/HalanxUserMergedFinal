package com.halanx.tript.userapp.Interfaces;


import com.halanx.tript.userapp.POJO.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by samarthgupta on 05/04/17.
 */

public interface DataInterface {

    @POST("/users/")
    Call<UserInfo> putDataOnServer(@Body UserInfo userData);

    @GET("/users/")
    Call<List<UserInfo>> getDataOnServer();


}
