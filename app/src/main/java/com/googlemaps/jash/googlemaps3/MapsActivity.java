package com.googlemaps.jash.googlemaps3;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.logging.Logger;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Logger logger;
    private int CurrtLong;
    private int CurrtLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
//                Logger.d(String.format("%f, %f", location.getLatitude(), location.getLongitude()));
//                drawMarker(location);
                CurrtLat = (int) location.getLatitude();
                CurrtLong = (int) location.getLongitude();
//                mLocationManager.removeUpdates(mLocationListener);
            } else {
//                Logger.d("Location is null");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
//    private LocationManager mLocationManager;
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
        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
        LatLng Toronto = new LatLng(43.6532, -79.3832);
        LatLng Hamilton = new LatLng(43.2557, -79.8711);
        LatLng currentL = new LatLng(CurrtLat, CurrtLong);
        mMap.addMarker(new MarkerOptions().position(Toronto).title("Toronto"));
        mMap.addMarker(new MarkerOptions().position(Hamilton).title("Hamilton"));
        mMap.addMarker(new MarkerOptions().position(Hamilton).title("currentL"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentL));
    }
}
