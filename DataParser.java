package com.example.fuelondemand;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    private HashMap<String,String>getSinglePlace(JSONObject googlePlaceJSON){
        HashMap<String,String>googlePlaceMap = new HashMap<>();
        String NameofPlace ="-NA-";
        String vicinity ="-NA-";
        String latitude ="";
        String longitude ="";
        String reference ="";

        try{
            if(!googlePlaceJSON.isNull("name")){
                NameofPlace =googlePlaceJSON.getString("name");
            }
            if(!googlePlaceJSON.isNull("vicinity")){
                vicinity =googlePlaceJSON.getString("vicinity");
            }
            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference =googlePlaceJSON.getString("reference");

            googlePlaceMap.put("place_name",NameofPlace);
            googlePlaceMap.put("vicinity",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("lng",longitude);
            googlePlaceMap.put("reference",reference);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return googlePlaceMap;
    }

    private List<HashMap<String,String>>getAllPlaces(JSONArray jsonArray){
        int counter = jsonArray.length();
        List<HashMap<String,String>>NearbyPlacesList = new ArrayList<>();
        HashMap<String ,String>NearbyPlaceMap = null;
        for(int i=0; i<counter;i++){
            try {
                NearbyPlaceMap = getSinglePlace((JSONObject) jsonArray.get(i));
                NearbyPlacesList.add(NearbyPlaceMap);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return NearbyPlacesList;
    }

    public List<HashMap<String,String>>parse(String jSONdata){
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return getAllPlaces(jsonArray);
    }
}
