package com.example.easytrail.ui.explore;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.easytrail.AnimalDetailActivity;
import com.example.easytrail.R;
import com.example.easytrail.StatusBar;
import com.example.easytrail.adapter.ExploreAnimalRecyclerViewAdapter;
import com.example.easytrail.model.AnimalResult;
import com.example.easytrail.networkconnection.NetworkConnection;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jaeger.library.StatusBarUtil;
import com.leinardi.android.speeddial.SpeedDialView;

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
    SpeedDialView speedDialView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_explore, container, false);
        StatusBarUtil.setLightMode(getActivity());
        StatusBar.setFragmentAdapter(this,root,false);


        if (!checkIfHasNetwork()){
            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(getContext())
                    .setTitle("Error")
                    .setMessage("No Internet Connection. Please Try Again")

                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            Fragment newFragment = new ExploreFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container,newFragment)
                                    .commit();
                        }


                    })
                    .setCancelable(false);
            dialog.show();

        }else{
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
                    final AnimalResult animal = animals.get(position);
                    final Intent intent = new Intent(getContext(), AnimalDetailActivity.class);
                    Bundle animalBundle = new Bundle();
                    animalBundle.putParcelable("animal",animal);
                    intent.putExtras(animalBundle);
                    final View imageView = view.findViewById(R.id.kbvExploreAnimalImage);
                    final View name = view.findViewById(R.id.exploreAnimal_name_tv);
                    final View habitat = view.findViewById(R.id.exploreAnimal_habitat_tv);
                    ViewCompat.setTransitionName(imageView, animal.getAnimal_image());
                    ViewCompat.setTransitionName(name,animal.getComm_name());
                    ViewCompat.setTransitionName(habitat,animal.getAnimal_habitat() + animal.getComm_name());
                    String nameTest = ViewCompat.getTransitionName(name);
                    final Pair<View,String> p1 = Pair.create(name,ViewCompat.getTransitionName(name));
                    final Pair<View,String> p2 = Pair.create(imageView,ViewCompat.getTransitionName(imageView));
                    final Pair<View,String> p3 = Pair.create(habitat,ViewCompat.getTransitionName(habitat));

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),p1,p2,p3);
                    startActivity(intent,options.toBundle());

                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });


//        speedDialView = root.findViewById(R.id.speed_dial);
//        speedDialView.setExpansionMode(SpeedDialView.ExpansionMode.TOP);
//        speedDialView.setMainFabOpenedDrawable(getResources().getDrawable(R.drawable.ic_close));
//        speedDialView.setMainFabClosedBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        speedDialView.setMainFabOpenedBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        speedDialView.setMainFabAnimationRotateAngle(180);
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_forest, R.drawable.ic_forest)
//                .setLabel("Forest")
//                .setFabBackgroundColor(getResources().getColor(R.color.forest))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_heath, R.drawable.ic_heath)
//                .setLabel("HeathLand")
//                .setFabBackgroundColor(getResources().getColor(R.color.heath))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_wood, R.drawable.ic_woodland)
//                .setLabel("Woodland")
//                .setFabBackgroundColor(getResources().getColor(R.color.forest))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_riparian, R.drawable.ic_riparian)
//                .setLabel("Riparian Zone")
//                .setFabBackgroundColor(getResources().getColor(R.color.riparian))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_swamp, R.drawable.ic_swamp)
//                .setLabel("Swamp")
//                .setFabBackgroundColor(getResources().getColor(R.color.swamp))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_coastal, R.drawable.ic_coastal)
//                .setLabel("Coastal Zone")
//                .setFabBackgroundColor(getResources().getColor(R.color.coastal))
//                .setFabImageTintColor(getResources().getColor(R.color.text_icons))
//                .create());
//        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
//            @Override
//            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
//                switch (speedDialActionItem.getId()){
//                    case R.id.fab_forest:
//                        Toast.makeText(getContext(), "clicked ",Toast.LENGTH_LONG).show();
//                        speedDialView.close();
//                    default:
//                        speedDialView.close();
//                }
//               return false;
//            }
//        });




            GetAllAnimals getAllAnimals = new GetAllAnimals();
            getAllAnimals.execute();


        }
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
                    String active_time = animalObject.getString("active_time");
                    String inhabit_area = animalObject.getString("inhabit_area");
                    saveData(animal_id,comm_name,sci_name,animal_type,size,diet,animal_location,conservation_status,regional_distribution,abundance,vic_conservation_status,act,animal_image,animal_habitat,animal_score, active_time, inhabit_area);

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

    private void saveData(int animal_id, String comm_name, String sci_name, String animal_type, String animal_size, String animal_diet, String animal_location, String conservation_status, String regional_distribution, String abundance, String vic_conservation_status, String act, String animal_image, String animal_habitat, int animal_score, String active_time, String inhabit_area){
        AnimalResult animalResult = new AnimalResult(animal_id,comm_name,sci_name,animal_type,animal_size,animal_diet,animal_location,conservation_status,regional_distribution,abundance,vic_conservation_status,act,animal_image,animal_habitat,animal_score, active_time, inhabit_area);
        animals.add(animalResult);
        adapter.addExploreAnimals(animals);
    }

    public boolean checkIfHasNetwork(){
        try {
            ConnectivityManager manger = (ConnectivityManager) getActivity()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manger.getActiveNetworkInfo();
            //return (info!=null && info.isConnected());//
            if(info != null){
                return info.isConnected();
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}