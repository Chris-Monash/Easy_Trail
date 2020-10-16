package com.example.easytrail.ui.explore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.easytrail.R;
import com.example.easytrail.adapter.ExploreAnimalRecyclerViewAdapter;
import com.example.easytrail.model.AnimalResult;
import com.example.easytrail.networkconnection.NetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {
    private List<AnimalResult> animals;
    private ExploreAnimalRecyclerViewAdapter adapter;
    NetworkConnection networkConnection = null;
    ProgressBar progressBar;
    ViewPager2 viewPager2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_explore, container, false);

        networkConnection = new NetworkConnection();
        progressBar = root.findViewById(R.id.exploreAnimal_progressbar);
        viewPager2 = root.findViewById(R.id.exploreAnimalViewPager);
        animals = new ArrayList<AnimalResult>();
        adapter = new ExploreAnimalRecyclerViewAdapter(getContext(),animals);
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
        adapter.setOnItemClickListener(new ExploreAnimalRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        GetAllAnimals getAllAnimals = new GetAllAnimals();
        getAllAnimals.execute();

        return root;
    }

    private class GetAllAnimals extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... voids) {
            return networkConnection.getAllAnimals();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray animalArray = new JSONArray(s);
                for (int i = 0; i < animalArray.length(); i++){
                    JSONObject animalObject = animalArray.getJSONObject(i);
                    int animal_id = animalObject.getInt("animal_id");
                    String comm_name = animalObject.getString("comm_name");
                    String sci_name = animalObject.getString("sci_name");
                    String animal_type = animalObject.getString("animal_type");
                    String size = animalObject.getString("size");
                    String diet = animalObject.getString("diet");
                    String animal_location = animalObject.getString("animal_location");
                    String conservation_status = animalObject.getString("conservation_status");
                    String regional_distribution = animalObject.getString("regional_distribution");
                    String abundance = animalObject.getString("abundance");
                    String vic_conservation_status = animalObject.getString("vic_conservation_status");
                    String act = animalObject.getString("act");
                    String animal_image = animalObject.getString("animal_image");
                    String animal_habitat = animalObject.getString("animal_habitat");
                    int animal_score = animalObject.getInt("score");
                    saveData(animal_id,comm_name,sci_name,animal_type,size,diet,animal_location,conservation_status,regional_distribution,abundance,vic_conservation_status,act,animal_image,animal_habitat,animal_score);

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
        AnimalResult animalResult = new AnimalResult(animal_id,comm_name,sci_name,animal_type,animal_size,animal_diet,animal_location,conservation_status,regional_distribution,abundance,vic_conservation_status,act,animal_image,animal_habitat,animal_score);
        animals.add(animalResult);
        adapter.addExploreAnimals(animals);
    }
}