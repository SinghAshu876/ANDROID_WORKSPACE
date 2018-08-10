package com.alkatv.app.services;

import com.alkatv.app.requests.LoginRequest;
import com.alkatv.app.requests.LogoutRequest;
import com.alkatv.app.requests.RegistrationRequest;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.utils.ResourceURIs;
import com.google.gson.JsonObject;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppUsersService {

    @POST(ResourceURIs.APP_USERS_LOGIN)
    Call<APIResponse> login(@Body LoginRequest loginRequest);

    @POST(ResourceURIs.APP_USERS_REGISTER)
    Call<APIResponse> register(@Body RegistrationRequest registrationRequest);

    @POST(ResourceURIs.APP_USERS_LOGOUT)
    Call<APIResponse> logout(@Body LogoutRequest logoutRequest);
   }
