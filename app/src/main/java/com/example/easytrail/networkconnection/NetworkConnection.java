package com.example.easytrail.networkconnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

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
}
