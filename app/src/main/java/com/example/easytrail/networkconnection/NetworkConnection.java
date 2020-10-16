package com.example.easytrail.networkconnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnection {
    private OkHttpClient client = null;
    private String results;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public NetworkConnection(){

        client = new OkHttpClient();
    }
    private static final String Base_URL="https://boiling-fortress-10870.herokuapp.com/api/";

    public String getAllTrails(){
        final String methodPath =  "trails";
        Request.Builder builder = new Request.Builder();
        builder.url(Base_URL + methodPath);
        Request request = builder.build();

        try{
            Response response = client.newCall(request).execute();
            results = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public String getAnimalForTrail(String trail_id){
        final String methodPath =  "details/" + trail_id;
        Request.Builder builder = new Request.Builder();
        builder.url(Base_URL + methodPath);
        Request request = builder.build();

        try{
            Response response = client.newCall(request).execute();
            results = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public String getAllAnimals(){
        final String methodPath =  "animals";
        Request.Builder builder = new Request.Builder();
        builder.url(Base_URL + methodPath);
        Request request = builder.build();

        try{
            Response response = client.newCall(request).execute();
            results = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }
}
