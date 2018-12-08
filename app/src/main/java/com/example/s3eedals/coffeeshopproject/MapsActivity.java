package com.example.s3eedals.coffeeshopproject;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
    public void onMapReady(GoogleMap googleMap) { mMap = googleMap;}

    public void searchAndShowOnMap(View view) throws IOException {
        EditText etPlaceToSearch = findViewById(R.id.etPlaceToSearch);
        String placeToSearch = etPlaceToSearch.getText().toString();

        if (placeToSearch.isEmpty()) {
            Toast.makeText(this, "Enter place to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Geocoder geocoder = new Geocoder(this);
        List<Address> list = geocoder.getFromLocationName(placeToSearch, 1);

        if (list.size() > 0) {
            Address address = list.get(0);
            String locality = address.getLocality();

            Toast.makeText(this, "Found "+locality, Toast.LENGTH_SHORT).show();

            double latitude = address.getLatitude();
            double longitude = address.getLongitude();

            gotoLocation(latitude, longitude, 15, locality);
        }
    }

    public void gotoLocation(double latitude, double longitude, int zoomLevel, String title) {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title));
        mMap.setMinZoomPreference(zoomLevel);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }



}
