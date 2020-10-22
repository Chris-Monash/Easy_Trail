package com.example.easytrail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.easytrail.model.AnimalResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import static android.view.View.GONE;


public class AnimalDetailActivity extends AppCompatActivity {
    private AnimalResult animalResult;
    private String animal_image_url;
    private String animal_name;
    private String animal_sci_name;
    private String animal_class;
    private String animal_habitat;
    private String animal_size;
    private String animal_diet;
    private String animal_conservation;
    private String animal_regionalDistribution;
    private String animal_abundance;
    private String animal_vicConservationStatus;
    private String animal_activeTime;
    private String animal_inhabitArea;
    private int animal_score;
    private String animal_find;

    Toolbar toolbar;
    ImageView animal_image_iv;
    TextView animal_name_tv;
    TextView animal_sci_name_tv;
    TextView animal_class_tv;
    TextView animal_habitat_tv;
    TextView animal_size_tv;
    TextView animal_diet_tv;
    TextView animal_conservation_tv;
    LinearLayout animal_conservation_tv_first;
    TextView animal_regionalDistribution_tv;
    LinearLayout animal_regionalDistribution_tv_first;
    TextView animal_abundance_tv;
    TextView animal_vicConservationStatus_tv;
    LinearLayout animal_vicConservationStatus_tv_first;
    TextView animal_activeTime_tv;
    LinearLayout animal_activeTime_tv_first;
    TextView animal_inhabitArea_tv;
    LinearLayout animal_inhabitArea_tv_first;
    TextView animal_score_tv;
    TextView animal_find_tv;
    ExtendedFloatingActionButton spotted_btn;

    CollapsingToolbarLayout coll_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animal_detail);
        postponeEnterTransition();
        StatusBar.setActivityAdapter(this,false);
        toolbar = findViewById(R.id.animal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        animal_image_iv = findViewById(R.id.expandedAnimalImage);
        animal_name_tv = findViewById(R.id.scrolling_animal_name_tv);
        animal_sci_name_tv = findViewById(R.id.scrolling_animal_sci_name_tv);
        animal_class_tv = findViewById(R.id.scrolling_animal_class_tv);
        animal_habitat_tv = findViewById(R.id.scrolling_animal_habitat_tv);
        animal_size_tv = findViewById(R.id.scrolling_animal_size_tv);
        animal_diet_tv = findViewById(R.id.scrolling_animal_diet_tv);
        animal_conservation_tv = findViewById(R.id.scrolling_animal_conservation_tv);
        animal_conservation_tv_first = findViewById(R.id.scrolling_animal_conservation_tv_first);
        animal_regionalDistribution_tv = findViewById(R.id.scrolling_animal_regionalDistribution_tv);
        animal_regionalDistribution_tv_first = findViewById(R.id.scrolling_animal_regionalDistribution_tv_first);
        animal_abundance_tv = findViewById(R.id.scrolling_animal_abundance_tv);
        animal_vicConservationStatus_tv = findViewById(R.id.scrolling_animal_vicConservationStatus_tv);
        animal_vicConservationStatus_tv_first = findViewById(R.id.scrolling_animal_vicConservationStatus_tv_first);
        animal_activeTime_tv = findViewById(R.id.scrolling_animal_activeTime_tv);
        animal_activeTime_tv_first = findViewById(R.id.scrolling_animal_activeTime_tv_first);
        animal_inhabitArea_tv = findViewById(R.id.scrolling_animal_inhabitArea_tv);
        animal_inhabitArea_tv_first = findViewById(R.id.scrolling_animal_inhabitArea_tv_first);
        animal_score_tv = findViewById(R.id.scrolling_animal_score_tv);
        animal_find_tv = findViewById(R.id.scrolling_animal_find_tv);

//        spotted_btn = findViewById(R.id.spotted_btn);


        Bundle bundle = getIntent().getExtras();
        animalResult = bundle.getParcelable("animal");
        animal_image_url = animalResult.getAnimal_image();
        animal_name = animalResult.getComm_name();
        animal_sci_name = animalResult.getSci_name();
        animal_class = animalResult.getAnimal_type();
        animal_habitat = animalResult.getAnimal_habitat();
        animal_size = animalResult.getAnimal_size();
        animal_diet = animalResult.getAnimal_diet();
        animal_conservation = animalResult.getConservation_status();
        animal_regionalDistribution = animalResult.getRegional_distribution();
        animal_abundance = animalResult.getAbundance();
        animal_vicConservationStatus = animalResult.getVic_conservation_status();
        animal_activeTime = animalResult.getActive_time();
        animal_inhabitArea = animalResult.getInhabit_area();
        animal_score = animalResult.getAnimal_score();
        animal_find = animalResult.getAnimal_location();


        animal_name_tv.setText(animal_name);
        animal_sci_name_tv.setText(animal_sci_name);
        animal_class_tv.setText(animal_class);
        animal_habitat_tv.setText(animal_habitat);
        animal_size_tv.setText(animal_size);
        animal_diet_tv.setText(animal_diet);
        if (animal_conservation.equals("")){
//            animal_conservation_tv.setVisibility(GONE);
            animal_conservation_tv_first.setVisibility(GONE);
        }else{
            animal_conservation_tv.setText(animal_conservation);
        }
        if (animal_regionalDistribution.equals("")){
//            animal_regionalDistribution_tv.setVisibility(GONE);
            animal_regionalDistribution_tv_first.setVisibility(GONE);
        }else{
            animal_regionalDistribution_tv.setText(animal_regionalDistribution);
        }

        animal_abundance_tv.setText(animal_abundance);

        if (animal_vicConservationStatus.equals("")){
//            animal_vicConservationStatus_tv.setVisibility(GONE);
            animal_vicConservationStatus_tv_first.setVisibility(GONE);
        }else{
            animal_vicConservationStatus_tv.setText(animal_vicConservationStatus);
        }

        if (animal_activeTime.equals("")){
//            animal_activeTime_tv.setVisibility(GONE);
            animal_activeTime_tv_first.setVisibility(GONE);
        }else{
            animal_activeTime_tv.setText(animal_activeTime);
        }

        if (animal_inhabitArea.equals("")){
//            animal_inhabitArea_tv.setVisibility(GONE);
            animal_inhabitArea_tv_first.setVisibility(GONE);
        }else{
            animal_inhabitArea_tv.setText(animal_inhabitArea);
        }
        animal_score_tv.setText(String.valueOf(animal_score));
        animal_find_tv.setText(animal_find);

        ViewCompat.setTransitionName(animal_image_iv, animalResult.getAnimal_image());
        ViewCompat.setTransitionName(animal_name_tv, animalResult.getComm_name());
        ViewCompat.setTransitionName(animal_habitat_tv, animalResult.getAnimal_habitat()+animalResult.getComm_name());
        String nameTest = ViewCompat.getTransitionName(animal_name_tv);

        Glide.with(this)
                .load(animal_image_url)//searchResults.get(position).getImageUrl())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                        startPostponedEnterTransition();
                        return false;
                    }
                })
                .into(animal_image_iv);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        coll_toolbar = findViewById(R.id.collapsing_AnimalToolbar);
        coll_toolbar.setTitle("");
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        coll_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
//        coll_toolbar.setContentScrimColor(this.getResources().getColor());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

}