package com.github.zhukdi.your_tour;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.github.zhukdi.your_tour.settings.AppSettings.currentLocation;
/**
 * Created by Dmitry on 5/15/2018.
 */

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private Boolean mLocationPermissionsGranted = false;

    private TextView sleepTextView;
    private TextView eatTextView;
    private TextView enjoyTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        sleepTextView = (TextView) findViewById(R.id.fra_home_sleep_btn);
        eatTextView = (TextView) findViewById(R.id.fra_home_eat_btn);
        enjoyTextView = (TextView) findViewById(R.id.fra_home_enjoy_btn);

        if (!mLocationPermissionsGranted) {
            getLocationPermission();
        }

        sleepTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaceList("cities");
            }
        });

        eatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaceList("restaurant");
            }
        });

        enjoyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlaceList("night_club");
            }
        });


    }

    public void openPlaceList(String placeType) {
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("TYPE", placeType);
        startActivity(intent);
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this,
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this,
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                getDeviceLocation();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            currentLocation = (Location) task.getResult();
                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(getApplicationContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }
}
