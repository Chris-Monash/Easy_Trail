package com.example.easytrail;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.adapter.AnimalRecyclerViewAdapter;
import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;
import com.example.easytrail.model.AnimalResult;
import com.example.easytrail.model.TrailResult;
import com.example.easytrail.networkconnection.NetworkConnection;
import com.example.easytrail.viewmodel.HistoryViewModel;
import com.example.easytrail.viewmodel.LocalAnimalViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    RoundedImageView confirmImage_iv;
    TextView confirmAnimalName_tv;
    TextView confirmAnimalType_tv;
    TextView confirmScore_tv;
    TextView confirmActive_tv;
    TextView confirmInhabit_tv;
    TextView confirmFind_tv;
    TextView spottingScore_tv;
    MaterialButton confirmSpotted_btn;
    MaterialButton confirmNot_btn;
    MaterialButton endTrail_btn;
    LocalAnimalDatabase db = null;
    HistoryViewModel historyViewModel;
    LocalAnimalViewModel localAnimalViewModel;
    private History history;
    private LocalAnimal localAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        StatusBar.setActivityAdapter(this,true);
        setContentView(R.layout.activity_spotting_animal);
        db = LocalAnimalDatabase.getInstance(this);
        toolbar = findViewById(R.id.spottingAnimal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        TrailResult trail = bundle.getParcelable("trail");
        trail_id = Integer.toString(trail.getTrail_id());
        trail_name = trail.getTrail_name();
        networkConnection = new NetworkConnection();
        spottingScore_tv = findViewById(R.id.spottingScore_tv);
        progressBar = findViewById(R.id.spottingAnimal_progressbar);
        viewPager2 = findViewById(R.id.spottingAnimalViewPager);
        animal_trailName_tv = findViewById(R.id.spottingAnimal_trailName_tv);
        endTrail_btn = findViewById(R.id.spottingAnimal_endTrail);
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
        GetLatestHis getLatestOne = new GetLatestHis();
        getLatestOne.execute();
        // get the latest history data

//        localAnimalViewModel = new ViewModelProvider(this).get(LocalAnimalViewModel.class);
//        localAnimalViewModel.initializeVars(getApplication());
//        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
//        historyViewModel.initializeVars(getApplication());
//        History temp = historyViewModel.getNewestOne();
//        spottingScore_tv.setText(Integer.toString(historyViewModel.getNewestOne().getCurrent_score()));
//        historyViewModel.getHistoryLiveData().observe(this, new Observer<History>() {
//            @Override
//            public void onChanged(@Nullable final History history) {
//
//
//                spottingScore_tv.setText(Integer.toString(history.getCurrent_score()));
//            }
//        });

//        materialCardView = findViewById(R.id.animalContainer_cardView);
//        materialCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                materialCardView.setChecked(!materialCardView.isChecked());
//                materialCardView.toggle();
//            }
//        });


        adapter.setOnItemClickListener(new AnimalRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final AnimalResult animal = animals.get(position);
//                Toast.makeText(getApplicationContext(), "clicked " + animal.getComm_name(),Toast.LENGTH_LONG).show();
//                Snackbar snackbar = Snackbar.make(view,"clicked " + animal.getComm_name(), Snackbar.LENGTH_LONG);
//                snackbar.show();
                final Intent intent = new Intent(SpottingAnimalActivity.this, AnimalDetailActivity.class);
                Bundle animalBundle = new Bundle();
                animalBundle.putParcelable("animal",animal);
                intent.putExtras(animalBundle);
                final View imageView = view.findViewById(R.id.kbvAnimalImage);
                final View name = view.findViewById(R.id.animal_name_tv);
                final View habitat = view.findViewById(R.id.animal_habitat_tv);
                ViewCompat.setTransitionName(imageView, animal.getAnimal_image());
                ViewCompat.setTransitionName(name,animal.getComm_name());
                ViewCompat.setTransitionName(habitat,animal.getAnimal_habitat() + animal.getComm_name());
                String nameTest = ViewCompat.getTransitionName(name);
                final Pair<View,String> p1 = Pair.create(name,ViewCompat.getTransitionName(name));
                final Pair<View,String> p2 = Pair.create(imageView,ViewCompat.getTransitionName(imageView));
                final Pair<View,String> p3 = Pair.create(habitat,ViewCompat.getTransitionName(habitat));

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SpottingAnimalActivity.this,p1,p2,p3);
                startActivity(intent,options.toBundle());
            }

            @Override
            public void onItemLongClick(View view, int position) {
                MaterialCardView materialCardView = view.findViewById(R.id.animalContainer_cardView);
                AnimalResult animal = animals.get(position);
                if (!materialCardView.isChecked()){
//                    materialCardView.setCardForegroundColor(ColorStateList.valueOf(Color.parseColor("#000000")));



                    String confirm_animalName = animal.getComm_name();
                    String confirm_animalType = animal.getAnimal_type();
                    String confirm_animalImage = animal.getAnimal_image();
                    int confirm_score = animal.getAnimal_score();
                    String confirm_abundance = animal.getAbundance();
                    String confirm_conservation = animal.getConservation_status();
                    String confirm_habitat = animal.getAnimal_habitat();
                    String confirm_activeTime = animal.getActive_time();
                    String confirm_inhabitArea = animal.getInhabit_area();
                    String confirm_find = animal.getAnimal_location();
//                GetConfirmInfo getConfirmInfo = new GetConfirmInfo();
//                getConfirmInfo.execute(confirm_animalName,confirm_animalType,confirm_animalImage);
//                AnimalResult animal = animals.get(position);
//                Toast.makeText(getApplicationContext(), "Long clicked " + animal.getComm_name(),Toast.LENGTH_LONG).show();
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SpottingAnimalActivity.this,R.style.BottomSheetDialogTheme);
                    View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheetContainer));

                    bottomSheetDialog.setContentView(bottomSheetView);

                    confirmImage_iv = bottomSheetView.findViewById(R.id.confirmation_image);
                    confirmAnimalName_tv = bottomSheetView.findViewById(R.id.confirmation_animalName);
                    confirmAnimalType_tv = bottomSheetView.findViewById(R.id.confirmation_animalType);
                    confirmScore_tv = bottomSheetView.findViewById(R.id.confirmation_animalScore);
                    confirmActive_tv = bottomSheetView.findViewById(R.id.confirmation_activeTime_tv);
                    confirmInhabit_tv = bottomSheetView.findViewById(R.id.confirmation_inhabit_tv);
                    confirmFind_tv = bottomSheetView.findViewById(R.id.confirmation_find_tv);
                    confirmNot_btn = (MaterialButton) bottomSheetView.findViewById(R.id.confirmation_not);
                    confirmSpotted_btn = (MaterialButton)bottomSheetView.findViewById(R.id.confirmation_spotted);

                    Glide.with(getApplicationContext())
                            .load(confirm_animalImage)//searchResults.get(position).getImageUrl())
//                            .asBitmap()
                            .centerCrop()
                            .transform(new RoundedCorners(50))
//                            .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(50, 0, RoundedCornersTransformation.CornerType.ALL)))
                            .placeholder(new ColorDrawable(Color.BLACK))
//                            .transition(DrawableTransitionOptions.withCrossFade())
                            .transition(DrawableTransitionOptions.withCrossFade(200))
                            .into(confirmImage_iv);
                    confirmAnimalName_tv.setText(confirm_animalName);
                    confirmAnimalType_tv.setText(confirm_animalType);
                    confirmScore_tv.setText(String.valueOf(confirm_score) + " points");
                    confirmActive_tv.setText(confirm_activeTime);
                    confirmInhabit_tv.setText(confirm_inhabitArea);
                    confirmFind_tv.setText(confirm_find);


                    confirmNot_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });

                    confirmSpotted_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            materialCardView.setChecked(true);
//                            materialCardView.setCardForegroundColor(ColorStateList.valueOf(Color.parseColor("#99C0C0C0")));
                            materialCardView.setCardForegroundColor(ColorStateList.valueOf(Color.parseColor("#CCC0C0C0")));
                            materialCardView.setRippleColor(ColorStateList.valueOf(Color.parseColor("#CC000000")));
                            bottomSheetDialog.dismiss();

                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateNowStr = sdf.format(date);
                            AddAnimal addAnimal = new AddAnimal();
                            addAnimal.execute(confirm_animalName,dateNowStr,Integer.toString(confirm_score), confirm_abundance, confirm_conservation, confirm_habitat, confirm_animalImage,Integer.toString(history.getHistory_id()), history.getCreated_date());
                            UpdateHis updateHis = new UpdateHis();
                            updateHis.execute(history.getHistory_id(),history.getCurrent_score()+confirm_score);



//                            int latest_score = confirm_score + historyViewModel.getNewestOne().getCurrent_score();
//                            historyViewModel.updateScore(historyViewModel.getNewestOne().getHistory_id(),latest_score);
//                            spottingScore_tv.setText(Integer.toString(historyViewModel.getNewestOne().getCurrent_score()));
//                            localAnimalViewModel.insert(new LocalAnimal(confirm_animalName,historyViewModel.getNewestOne().getHistory_id()));
                            Snackbar snackbar = Snackbar.make(view,animal.getComm_name() + " has been marked", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    materialCardView.setChecked(false);
                                    materialCardView.setCardForegroundColor(ColorStateList.valueOf(Color.TRANSPARENT));
                                    materialCardView.setRippleColor(ColorStateList.valueOf(Color.parseColor("#33000000")));
                                    GetLatestAni getLatestAni = new GetLatestAni();
                                    getLatestAni.execute();
//                                    int modifiedScore = historyViewModel.getNewestOne().getCurrent_score() - confirm_score;
//                                    historyViewModel.updateScore(historyViewModel.getNewestOne().getHistory_id(), modifiedScore);
//                                    spottingScore_tv.setText(Integer.toString(historyViewModel.getNewestOne().getCurrent_score()));
//                                    localAnimalViewModel.deleteByIDs(historyViewModel.getNewestOne().getHistory_id(),localAnimalViewModel.findLatestOne().getLocalAnimal_id());

                                    Snackbar snackbar1 = Snackbar.make(view,animal.getComm_name() + " has been unmarked",Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });
                            snackbar.show();

                        }
                    });

                    bottomSheetDialog.show();
                }else{
                    Toast.makeText(getApplicationContext(), animal.getComm_name()+"has selected",Toast.LENGTH_LONG).show();
                }

            }
        });


        endTrail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpottingAnimalActivity.this,EndTrailActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SpottingAnimalActivity.this).toBundle());
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
                    String active_time = animalObject.getString("active_time");
                    String inhabit_area = animalObject.getString("inhabit_area");
                    saveData(animal_id, comm_name, sci_name, animal_type, animal_size, animal_diet, animal_location, conservation_status, regional_distribution, abundance, vic_conservation_status, act, animal_image, animal_habitat, animal_score, active_time, inhabit_area);
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

//    private class GetConfirmInfo extends AsyncTask<String,Void,String>{
//        @Override
//        protected String doInBackground(String... strings) {
//            Glide.with(getApplicationContext())
//                    .load(strings[2])//searchResults.get(position).getImageUrl())
//                    .placeholder(new ColorDrawable(Color.BLACK))
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .into(confirmImage);
//            confirmAnimalName.setText(strings[0]);
//            confirmAnimalType.setText(strings[1]);
//            return "1";
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SpottingAnimalActivity.this,R.style.BottomSheetDialogTheme);
//            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheetContainer));
//            bottomSheetDialog.setContentView(bottomSheetView);
//            bottomSheetDialog.show();
//        }
//    }

    private class GetLatestHis extends AsyncTask<Void,Void,History>{
        @Override
        protected History doInBackground(Void... voids) {
            return history = db.historyDAO().getNewestOne();
        }

        @Override
        protected void onPostExecute(History history) {
            spottingScore_tv.setText(String.valueOf(history.getCurrent_score()));
        }
    }

    private class AddAnimal extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            db.localAnimalDAO().insert(new LocalAnimal(strings[0],strings[1],Integer.parseInt(strings[2]),strings[3], strings[4],strings[5], strings[6], Integer.parseInt(strings[7]),strings[8]));
            return null;
        }

    }

    private class UpdateHis extends AsyncTask<Integer,Void,Void>{
        @Override
        protected Void doInBackground(Integer... integers) {
            db.historyDAO().updateScores(integers[0],integers[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            GetLatestHis getLatestOne = new GetLatestHis();
            getLatestOne.execute();
        }
    }

    private class GetLatestAni extends AsyncTask<Void,Void,LocalAnimal>{
        @Override
        protected LocalAnimal doInBackground(Void... voids) {
            return localAnimal = db.localAnimalDAO().findLatestOne();
        }

        @Override
        protected void onPostExecute(LocalAnimal localAnimal) {

            DeleteAni deleteAni = new DeleteAni();
            deleteAni.execute(localAnimal);
        }
    }

    private class DeleteAni extends AsyncTask<LocalAnimal,Void,Integer>{
        @Override
        protected Integer doInBackground(LocalAnimal... localAnimals) {
            db.localAnimalDAO().delete(localAnimals[0]);
            return localAnimals[0].getLocalAnimal_score();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            UpdateHis updateHis = new UpdateHis();
            updateHis.execute(history.getHistory_id(),history.getCurrent_score()-integer);
        }
    }


    private void saveData(int animal_id, String comm_name, String sci_name, String animal_type, String animal_size, String animal_diet, String animal_location, String conservation_status, String regional_distribution, String abundance, String vic_conservation_status, String act, String animal_image, String animal_habitat, int animal_score, String active_time, String inhabit_area){
        AnimalResult animalResult = new AnimalResult(animal_id, comm_name, sci_name, animal_type, animal_size, animal_diet, animal_location, conservation_status, regional_distribution, abundance, vic_conservation_status, act, animal_image, animal_habitat, animal_score, active_time, inhabit_area);
        animals.add(animalResult);
        adapter.addAnimals(animals);
    }
    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}