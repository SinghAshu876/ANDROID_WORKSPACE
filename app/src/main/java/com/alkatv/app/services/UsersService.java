package com.alkatv.app.services;

import com.alkatv.app.requests.LoginRequest;
import com.alkatv.app.requests.LogoutRequest;
import com.alkatv.app.requests.RegistrationRequest;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.utils.ResourceURIs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsersService {

    @POST(ResourceURIs.USERS_SEARCH)
    Call<APIResponse> search(@Body String id);


   }
