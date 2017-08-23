package com.laeb.laebproject.create_field_fragments;

import android.app.Fragment;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import com.laeb.laebproject.CreateFieldActivity;
import com.laeb.laebproject.R;
import com.laeb.laebproject.model.CustomBinder;
import com.laeb.laebproject.model.FieldInfo;

/**
 * Created by tariq on 8/18/2017.
 */

public class MapFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private MapView mapView;
    private GoogleMap googleMap;
    LocationManager locationManager;
    LocationRequest mLocationRequest;
    Marker currLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LatLng latLng;
    FieldInfo fieldInfo;
    CustomBinder oCustom;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        oCustom = (CustomBinder) getArguments().get("complexObject");
        fieldInfo = oCustom.getField();
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        View b = (View) view.findViewById(R.id.nextMapp);
        View currloc = (View) view.findViewById(R.id.btnCurrentLocation);
        currloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildGoogleApiClient();

                mGoogleApiClient.connect();
            }
        });

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment
        b.setOnClickListener(this);

        return view;
    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextMapp:
<<<<<<< HEAD
                //fieldInfo.latitude = latLng.latitude;
                //fieldInfo.longitude = latLng.longitude;
=======
//                fieldInfo.latitude = latLng.latitude;
//                fieldInfo.longitude = latLng.longitude;
>>>>>>> 98326378f87eb6d78270524e43dd431c27732153

                Toast.makeText(getActivity(), fieldInfo.name, Toast.LENGTH_SHORT).show();
                SoicalMediaFragment fragment = new SoicalMediaFragment();
                Bundle args = new Bundle();
                CustomBinder oCustom = new CustomBinder();
                oCustom.setList(fieldInfo);
                args.putSerializable("complexObject", oCustom);
                fragment.setArguments(args);
                ((CreateFieldActivity) getActivity()).addFragment(fragment);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMyLocationEnabled(true);


    }

    @Override
    public void onConnected(Bundle bundle) {

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = googleMap.addMarker(markerOptions);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        //place marker at current position
        //mGoogleMap.clear();
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currLocationMarker = googleMap.addMarker(markerOptions);


        //zoom to current position:
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));


    }
}