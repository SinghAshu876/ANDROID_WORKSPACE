package com.alkatv.app.services;

import com.alkatv.app.requests.LoginRequest;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.utils.ResourceURIs;
import com.google.gson.JsonObject;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST(ResourceURIs.APP_USERS_LOGIN)
    Call<APIResponse> login(@Body LoginRequest loginRequest);
   }
