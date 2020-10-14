package com.example.easytrail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.easytrail.model.AnimalResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.example.easytrail.R.color.colorPrimary;
import static com.example.easytrail.R.color.white;


public class AnimalDetailActivity extends AppCompatActivity {
    private AnimalResult animalResult;
    private String animal_image_url;
    private String animal_name;
    private String animal_sci_name;
    private String animal_class;
    private String animal_habitat;
    Toolbar toolbar;
    ImageView animal_image_iv;
    TextView animal_name_tv;
    TextView animal_sci_name_tv;
    TextView animal_class_tv;
    TextView animal_habitat_tv;
    CollapsingToolbarLayout coll_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animal_detail);
        StatusBar.setActivityAdapter(this,true);
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

        Bundle bundle = getIntent().getExtras();
        animalResult = bundle.getParcelable("animal");
        animal_image_url = animalResult.getAnimal_image();
        animal_name = animalResult.getComm_name();
        animal_sci_name = animalResult.getSci_name();
        animal_class = animalResult.getAnimal_type();
        animal_habitat = animalResult.getAnimal_habitat();


        animal_name_tv.setText(animal_name);
        animal_sci_name_tv.setText(animal_sci_name);
        animal_class_tv.setText(animal_class);
        animal_habitat_tv.setText(animal_habitat);

        ViewCompat.setTransitionName(animal_image_iv, animalResult.getAnimal_image());
        ViewCompat.setTransitionName(animal_name_tv, animalResult.getComm_name());
        ViewCompat.setTransitionName(animal_habitat_tv, animalResult.getAnimal_habitat()+animalResult.getComm_name());
        String nameTest = ViewCompat.getTransitionName(animal_name_tv);
        postponeEnterTransition();
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