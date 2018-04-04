package com.googlemaps.jash.googlemaps3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;
    private LocationManager manager;
    private LocationListener locationListener;

    //    ArrayList<String> CurrtLong = new ArrayList<String>();
//    ArrayList<String> CurrtLat = new ArrayList<String>();
//
//        private void setcustLong(ArrayList CurrtLong){
//            this.CurrtLong = CurrtLong;
//        }
//        private void setcustLat(ArrayList CurrtLat){
//                this.CurrtLat = CurrtLat;
//        }
    public class MapPointsDefinition {
        String title;
        ArrayList<Double> latitude;
        ArrayList<Double> longitude;

        public MapPointsDefinition(ArrayList<Double> latitude, ArrayList<Double> longitude) {
            this.title = title;
            this.latitude =  latitude;
            this.longitude = longitude;
        }
    }

    public class MyMarkerData {
        LatLng latLng;
        String title;
        Bitmap bitmap;

        public LatLng getLatLng() {
            return latLng;
        }

        public void setLatLng(LatLng latLng) {
            this.latLng = latLng;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //request the location update thru location manager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //get the latitude and longitude from the location
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getSubLocality()+":";
                    result += addresses.get(0).getLocality()+":";
                    result += addresses.get(0).getCountryCode();
                    LatLng latLng = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                    mMap.setMaxZoomPreference(20);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    HashMap<Marker, MyMarkerData> mDataMap = new HashMap<>();

    public void drawMarkers(ArrayList<MyMarkerData> data, GoogleMap googleMap) {
        Marker m;
        for (MyMarkerData object: data) {
            m = googleMap.addMarker(new MarkerOptions()
                    .position(object.getLatLng())
                    .title(object.getTitle())
                    .icon(BitmapDescriptorFactory.fromBitmap(object.getBitmap())));

            mDataMap.put(m, object);
        }
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
        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);

//        ArrayList<Map<Double, Double>> latlongList = new ArrayList<>();
        ArrayList<Double> latitude = new ArrayList<Double>();
        ArrayList<Double> longitude = new ArrayList<Double>();
        ArrayList<String> City = new ArrayList<String>();

        latitude.add( 43.6532);
        latitude.add(43.2557);
        latitude.add(43.5182991);
        longitude.add( -79.3832);
        longitude.add(-79.8711);
        longitude.add( -79.8774042);
        City.add("Toronto");
        City.add("Hamilton");
        City.add("Milton");

//        latlongList.add(latitude);
        MapPointsDefinition myMapPointsDefinition= new MapPointsDefinition( latitude, longitude);


        LatLng Toronto = new LatLng(43.6532, -79.3832);
        LatLng Hamilton = new LatLng(43.2557, -79.8711);
        for (int i = 0; i < latitude.size(); i++) {
            LatLng Location = new LatLng(Double.parseDouble(String.valueOf(latitude.get(i))),  Double.parseDouble(String.valueOf(longitude.get(i))));
            mMap.addMarker(new MarkerOptions().position(Location).title(City.get(i).toString().toUpperCase()));

            TextView tv= (TextView) findViewById(R.id.textView);
            tv.setText("Text = "+latitude.toString());
        }
//            LatLng currentL = new LatLng(CurrtLat, CurrtLong);

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//            mMap.addMarker(new MarkerOptions().position(Toronto).title("Test1"));

//        mMap.addMarker(new MarkerOptions().position(Hamilton).title("currentL"));

//            mMap.moveCamera(CameraUpdateFactory.newLatLng(Hamilton));
        float zoomLevel = (float) 12.0;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hamilton, zoomLevel));

    }
    @Override
    protected void onPause() {
        super.onPause();
        manager.removeUpdates(locationListener);
        Log.i("onPause...","paused");
    }

}