package com.alkatv.app.services;


import com.alkatv.app.requests.RegistrationRequest;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.utils.ResourceURIs;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationService {

    @POST(ResourceURIs.APP_USERS_REGISTER)
    Call<APIResponse> register(@Body RegistrationRequest registrationRequest);

}
