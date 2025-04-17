package com.example.fuelondemand;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FetchData extends AsyncTask<Object,String,String>{

    String googleNearbyPlaceData;
    GoogleMap googleMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        googleMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleNearbyPlaceData = downloadUrl.retrieveUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleNearbyPlaceData;
    }


    protected void onPostExecute(String s){
        List<HashMap<String ,String >>nearbyPlacesList =null;
        DataParser dataParser= new DataParser();
        nearbyPlacesList = dataParser.parse(s);

        DisplayNearbyPlaces(nearbyPlacesList);
    }


    private void DisplayNearbyPlaces(List<HashMap<String ,String >>nearbyPlacesList){
        for(int i=0;i<nearbyPlacesList.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String>googleNearbyPlaces = nearbyPlacesList.get(i);
            String nameofPlace = googleNearbyPlaces.get("NameofPlace");
            String vicinity = googleNearbyPlaces.get("vicinity");
            double lat = Double.parseDouble(googleNearbyPlaces.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlaces.get("lng"));

            LatLng latLng = new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(nameofPlace + " : " + vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        }
    }



}
