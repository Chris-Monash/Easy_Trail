package com.example.easytrail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytrail.adapter.AnimalRecyclerViewAdapter;
import com.example.easytrail.model.AnimalResult;
import com.example.easytrail.model.TrailResult;
import com.example.easytrail.networkconnection.NetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpottingAnimalActivity extends AppCompatActivity {

    private List<AnimalResult> animals;
    private AnimalRecyclerViewAdapter adapter;
    private String trail_id;
    private String trail_name;
    ProgressBar progressBar;
    TextView animal_trailName_tv;
    ViewPager2 viewPager2;
    NetworkConnection networkConnection = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotting_animal);
        toolbar = findViewById(R.id.spottingAnimal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        TrailResult trail = bundle.getParcelable("trail");
        trail_id = Integer.toString(trail.getTrail_id());
        trail_name = trail.getTrail_name();
        networkConnection = new NetworkConnection();
        progressBar = findViewById(R.id.spottingAnimal_progressbar);
        viewPager2 = findViewById(R.id.spottingAnimalViewPager);
        animal_trailName_tv = findViewById(R.id.spottingAnimal_trailName_tv);
        animal_trailName_tv.setText(trail_name);
        animals = new ArrayList<AnimalResult>();
        adapter = new AnimalRecyclerViewAdapter(getApplicationContext(),animals);
        viewPager2.setAdapter(adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        GetAllAnimalsForTrail getAllAnimalsForTrail = new GetAllAnimalsForTrail();
        getAllAnimalsForTrail.execute(trail_id);


        adapter.setOnItemClickListener(new AnimalRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AnimalResult animal = animals.get(position);
                Toast.makeText(getApplicationContext(), "clicked " + animal.getComm_name(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }


    private class GetAllAnimalsForTrail extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return networkConnection.getAnimalForTrail(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray resultArray = new JSONArray(s);
                for (int i = 0; i < resultArray.length(); i++){
                    JSONObject detailObject = resultArray.getJSONObject(i);
                    JSONObject animalObject = detailObject.getJSONObject("animals");
                    int animal_id = animalObject.getInt("animal_id");
                    String comm_name = animalObject.getString("comm_name");
                    String sci_name = animalObject.getString("sci_name");
                    String animal_type = animalObject.getString("animal_type");
                    String animal_size = animalObject.getString("size");
                    String animal_diet = animalObject.getString("diet");
                    String animal_location = animalObject.getString("animal_location");
                    String conservation_status = animalObject.getString("conservation_status");
                    String regional_distribution = animalObject.getString("regional_distribution");
                    String abundance = animalObject.getString("abundance");
                    String vic_conservation_status = animalObject.getString("vic_conservation_status");
                    String act = animalObject.getString("act");
                    String animal_image = animalObject.getString("animal_image");
                    String animal_habitat = animalObject.getString("animal_habitat");
                    int animal_score = animalObject.getInt("score");
                    saveData(animal_id, comm_name, sci_name, animal_type, animal_size, animal_diet, animal_location, conservation_status, regional_distribution, abundance, vic_conservation_status, act, animal_image, animal_habitat, animal_score);
                }
                viewPager2.setAdapter(adapter);
                viewPager2.setClipToPadding(false);
                viewPager2.setClipChildren(false);
                viewPager2.setOffscreenPageLimit(3);
                viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                    @Override
                    public void transformPage(@NonNull View page, float position) {
                        float r = 1- Math.abs(position);
                        page.setScaleY(0.85f + r * 0.15f);
                    }
                });

                viewPager2.setPageTransformer(compositePageTransformer);
                progressBar.setVisibility(View.GONE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void saveData(int animal_id, String comm_name, String sci_name, String animal_type, String animal_size, String animal_diet, String animal_location, String conservation_status, String regional_distribution, String abundance, String vic_conservation_status, String act, String animal_image, String animal_habitat, int animal_score){
        AnimalResult animalResult = new AnimalResult(animal_id, comm_name, sci_name, animal_type, animal_size, animal_diet, animal_location, conservation_status, regional_distribution, abundance, vic_conservation_status, act, animal_image, animal_habitat, animal_score);
        animals.add(animalResult);
        adapter.addAnimals(animals);
    }
}