package com.example.easytrail.ui.trail;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.easytrail.R;
import com.example.easytrail.TrailDetailActivity;
import com.example.easytrail.adapter.TrailRecyclerViewAdapter;
import com.example.easytrail.model.TrailResult;
import com.example.easytrail.networkconnection.NetworkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrailFragment extends Fragment {

    private TrailViewModel trailViewModel;
    private List<TrailResult> trails;
    private TrailRecyclerViewAdapter adapter;
    ProgressBar progressBar;
    ViewPager2 viewPager2;
    NetworkConnection networkConnection = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trailViewModel = ViewModelProviders.of(this).get(TrailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trail, container, false);

        networkConnection = new NetworkConnection();
        progressBar = root.findViewById(R.id.progressbar);
        viewPager2 = root.findViewById(R.id.trailViewPager);
        trails = new ArrayList<TrailResult>();
        adapter = new TrailRecyclerViewAdapter(getContext(),trails);
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
        GetAllTrails getAllTrails = new GetAllTrails();
        getAllTrails.execute();
        adapter.setOnItemClickListener(new TrailRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TrailResult trail = trails.get(position);
                Intent intent = new Intent(getActivity(), TrailDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("trail",trail);
                intent.putExtras(bundle);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
//                Toast.makeText(getContext(), "clicked " + trail.getTrail_name(),Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), AnimalDetail.class);
//                intent.putExtra("animal_guid", animal.guid());
//                intent.putExtra("imageUrl",animal.getImageUrl());
//                getActivity().startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        return root;

    }




    private class GetAllTrails extends AsyncTask<Void,Void,String>{//get all trails from database
        @Override
        protected String doInBackground(Void... voids) {
            return networkConnection.getAllTrails();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray resultArray = new JSONArray(s);
                for (int i = 0; i < resultArray.length(); i++){
                    JSONObject trailObject = resultArray.getJSONObject(i);
                    int trail_id = trailObject.getInt("trail_id");
                    String trail_name = trailObject.getString("trail_name");
                    String address = trailObject.getString("address");
                    String route_type = trailObject.getString("route_type");
                    String distance = trailObject.getString("distance");
                    String complete_time = trailObject.getString("complete_time");
                    String facilities = trailObject.getString("facilities");
                    String description = trailObject.getString("description");
                    String trail_image = trailObject.getString("trail_image");
                    String environment = trailObject.getString("environment");
                    JSONObject difficultiesObject = trailObject.getJSONObject("difficulties");
                    String difficulty_name = difficultiesObject.getString("difficulty_name");
                    String difficulty_image = difficultiesObject.getString("difficulty_image");
                    saveData(trail_id,trail_name, address, route_type, distance, complete_time, facilities, description, trail_image, environment, difficulty_name, difficulty_image);
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
    private void saveData(int trail_id, String trail_name, String address, String route_type, String distance, String complete_time, String facilities, String description, String trail_image, String environment, String difficulty_name, String difficulty_image){
        TrailResult trailResult = new TrailResult(trail_id,trail_name, address, route_type, distance, complete_time, facilities, description, trail_image, environment, difficulty_name, difficulty_image);
        trails.add(trailResult);
        adapter.addTrails(trails);
    }
}