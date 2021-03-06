package com.example.guvelo.navdrawertest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 100;
    float distanciaMts;
    float distanciaKm;
    LatLng marca2;
    LatLng marca3;
    Location loc02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            mMap = googleMap;

            // Android version is lesser than 6.0 or the permission is already granted.
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (locationGPS != null){
                marca2 = new LatLng(locationGPS.getLatitude(), locationGPS.getLongitude());}
            else{
                marca2 = new LatLng(locationNet.getLatitude(), locationNet.getLongitude());}
           // marca3 = new LatLng(locationGPS.getLatitude(), locationGPS.getLongitude()+0.005);


            mMap.moveCamera(CameraUpdateFactory.newLatLng(marca2));
            Location loc01 = new Location("locacion 01");
            loc02 = new Location("locacion 02");

            loc01.setLatitude(marca2.latitude);
            loc01.setLongitude(marca2.longitude);


            distanciaMts = loc01.distanceTo(loc02); //En Metros
            distanciaKm = loc01.distanceTo(loc02) /1000; //Convertir a Kilometros

            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    Toast.makeText(getBaseContext(), "Distancia entre los dos puntos (Metros) : " + distanciaMts, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Nuevo punto"));
                    marca3 = new LatLng(latLng.latitude, latLng.longitude);
                    loc02.setLatitude(marca3.latitude);
                    loc02.setLongitude(marca3.longitude);
                    }
            });


        }
    }

    public void accion(View v){
        Toast.makeText(this, "Distancia entre los dos puntos (Metros) : " + distanciaMts, Toast.LENGTH_SHORT).show();
    }
}

