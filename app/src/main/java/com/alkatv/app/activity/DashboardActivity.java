package com.alkatv.app.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alkatv.app.R;
import com.alkatv.app.fragments.HomeFragment;
import com.alkatv.app.fragments.SearchFragment;
import com.alkatv.app.fragments.SendFeedbackFragment;
import com.alkatv.app.fragments.SettingsFragment;
import com.alkatv.app.responses.APIResponse;
import com.alkatv.app.responses.Error;
import com.alkatv.app.services.APIClient;
import com.alkatv.app.services.AppUsersService;
import com.alkatv.app.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SendFeedbackFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    private Handler mHandler;
    private int menuItemId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mHandler = new Handler();

    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (menuItemId != R.id.nav_home) {
            loadHomeFragment();
            return;
        }
        if (menuItemId == R.id.nav_home) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        menuItemId = item.getItemId();

        if (menuItemId == R.id.nav_home) {
            loadHomeFragment();
        } else if (menuItemId == R.id.nav_search) {
            loadSearchFragment();
        } else if (menuItemId == R.id.nav_settings) {
            loadSettingsFragment();
        } else if (menuItemId == R.id.nav_send) {
            loadSendFragment();
        } else if (menuItemId == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {

        APIClient.getClient().create(AppUsersService.class).logout(null).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                finishAffinity();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                finishAffinity();
            }
        });

    }

    private void loadSendFragment() {
        getSupportActionBar().setTitle(R.string.nav_send_feedback);
        final SendFeedbackFragment feedbackFragment = new SendFeedbackFragment();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = feedbackFragment;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, getString(R.string.nav_send_feedback));
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private void loadSettingsFragment() {
        getSupportActionBar().setTitle(R.string.nav_settings);
        final SettingsFragment settingsFragment = new SettingsFragment();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = settingsFragment;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, getString(R.string.nav_settings));
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


    }

    private void loadSearchFragment() {
        getSupportActionBar().setTitle(R.string.nav_search);
        final SearchFragment searchFragment = new SearchFragment();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = searchFragment;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, getString(R.string.nav_search));
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

    }

    private void loadHomeFragment() {
        getSupportActionBar().setTitle(R.string.nav_home);
        final HomeFragment homeFragment = new HomeFragment();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = homeFragment;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, getString(R.string.nav_home));
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }
}
