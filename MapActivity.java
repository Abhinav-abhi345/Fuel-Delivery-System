package com.example.fuelondemand;

import static androidx.core.location.LocationManagerCompat.getCurrentLocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    //Toolbar toolbar;
    Button goback;
    SearchView searchView;
    Marker marker;

    GoogleMap gmap;
    private static final int REQUEST_CODE = 101;
    Location currentlocation;
    FrameLayout map;
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment mapFragment;
    private double latitude,longitude;
    private int ProximityRadius =10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        goback = (Button) findViewById(R.id.btBack);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, HomePage.class);
                //selectVehileIntent.putExtra("lat",marker.getPosition().latitude);
                //selectVehileIntent.putExtra("lng",marker.getPosition().longitude);
                startActivity(intent);

            }
        });


        searchView = findViewById(R.id.idSearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String loc = searchView.getQuery().toString();
                if(loc == null){
                    Toast.makeText(MapActivity.this, "Location not found", Toast.LENGTH_SHORT).show();
                }else {
                    Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                    try{
                        List<Address> addressList = geocoder.getFromLocationName(loc,1);
                        if(addressList.size() > 0){
                            LatLng latLng = new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude());
                            if(marker != null){
                                marker.remove();
                            }
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(loc);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,5);
                            gmap.animateCamera(cameraUpdate);
                            marker = gmap.addMarker(markerOptions);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        map = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

    }

    private String getUrl(double latitude,double longitude,String petrol){
        StringBuilder googleUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleUrl.append("location=" + latitude + "," + longitude);
        googleUrl.append("&radius" + ProximityRadius);
        googleUrl.append("&type=" + petrol);
        googleUrl.append("&sensor=true");
        googleUrl.append("&key=" + getResources().getString(R.string.google_map_key));

        Log.d("MapActivity","url =" + googleUrl.toString());

        return googleUrl.toString();
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentlocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert mapFragment != null;
                    mapFragment.getMapAsync(MapActivity.this);
                }
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        latitude = currentlocation.getLatitude();
        longitude = currentlocation.getLongitude();
        this.gmap = googleMap;
        LatLng latLng = new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(latLng).title("location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
        }
    }
}