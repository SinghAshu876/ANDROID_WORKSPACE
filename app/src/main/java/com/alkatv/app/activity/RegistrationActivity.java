package com.alkatv.app.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.alkatv.app.R;
import com.alkatv.app.requests.RegistrationRequest;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.responses.Error;
import com.alkatv.app.services.APIClient;
import com.alkatv.app.services.RegistrationService;
import com.alkatv.app.utils.AppConstants;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private static final String LOG_TAG = "RegistrationActivity";

    private UserRegistrationTask userRegistrationTask = null;

    private TextView mMobileNoView;
    private TextView mPasswordView;
    private TextView mNameView;
    private View mProgressView;
    private View mRegistrationFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent intent = getIntent();

        mMobileNoView = (TextView) findViewById(R.id.mobileNo);
        mNameView = (EditText) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });

        Button mMobileRegisterButton = (Button) findViewById(R.id.mobile_register_button);
        mMobileRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        mRegistrationFormView = findViewById(R.id.registration_form);
        mProgressView = findViewById(R.id.registration_progress);
    }

    private void attemptRegistration() {
        if (userRegistrationTask != null) {
            return;
        }

        mMobileNoView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);


        String mobileNo = mMobileNoView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(mobileNo)) {
            mMobileNoView.setError(getString(R.string.error_field_required));
            focusView = mMobileNoView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(name) && !isNameValid(name)) {
            mNameView.setError(getString(R.string.error_invalid_name));
            focusView = mNameView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!isMobileNoValid(mobileNo)) {
            mMobileNoView.setError(getString(R.string.error_invalid_mobNo));
            focusView = mMobileNoView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            userRegistrationTask = new UserRegistrationTask(mobileNo, password, name);
            userRegistrationTask.execute((Void) null);
        }
    }

    private boolean isMobileNoValid(String mobileNo) {
        return mobileNo.length() == 10;
    }

    private boolean isNameValid(String name) {
        return name.length() >= 5;
    }

    private boolean isPasswordValid(String password) {
        return password.length() == 4;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegistrationFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class UserRegistrationTask extends AsyncTask<Void, Void, Void> {

        private final String mMobNo;
        private final String mPassword;
        private final String mName;

        UserRegistrationTask(String mobileNo, String password, String name) {
            mMobNo = mobileNo;
            mPassword = password;
            mName = name;
        }

        @Override
        protected Void doInBackground(Void... params) {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setName(mName);
            registrationRequest.setPwd(mPassword);
            registrationRequest.setUserName(mMobNo);
            Log.i(LOG_TAG, registrationRequest.getUserName());
            Log.i(LOG_TAG, registrationRequest.getPwd());
            Log.i(LOG_TAG, registrationRequest.getName());

            APIClient.getClient().create(RegistrationService.class).register(registrationRequest).enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegistrationActivity.this);
                    if (response != null && response.body() != null && response.body().getResponseType() != null && response.body().getResponseType().equals(AppConstants.SUCCESS)) {
                        dlgAlert.setMessage(response.body().getMessage());
                        dlgAlert.setTitle(AppConstants.SUCCESS);
                        dlgAlert.setPositiveButton(AppConstants.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                navigateToLoginActivity();
                            }
                        });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                    } else {
                        List<com.alkatv.app.responses.Error> errorList = response.body().getError();
                        Error error = errorList.get(0);
                        if (error != null) {
                            dlgAlert.setMessage(error.getErrorDesc());
                            dlgAlert.setTitle(AppConstants.ERROR);
                            dlgAlert.setPositiveButton(AppConstants.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        } else {
                            dlgAlert.setMessage(AppConstants.DEFAULT_ERROR_MSG);
                            dlgAlert.setTitle(AppConstants.ERROR);
                            dlgAlert.setPositiveButton(AppConstants.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    Log.i("onFailure", t.getMessage());
                    Toast.makeText(RegistrationActivity.this, R.string.default_toast_message, Toast.LENGTH_LONG).show();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            userRegistrationTask = null;
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            userRegistrationTask = null;
            showProgress(false);
        }
    }

    public void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
