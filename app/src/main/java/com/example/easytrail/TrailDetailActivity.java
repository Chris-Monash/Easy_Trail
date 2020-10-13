package com.example.easytrail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;
import com.example.easytrail.model.TrailResult;
import com.example.easytrail.viewmodel.HistoryViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.easytrail.R.color.colorPrimary;
import static com.example.easytrail.R.color.primary_dark_material_dark;


public class TrailDetailActivity extends AppCompatActivity {
    private String trailName;
    private String trailImage;
    private String trailGradeImage;
    private String trailGrade;
    private String trailAbout;
    private String trailDistance;
    private String trailCompleteTime;
    private TrailResult trail;
    Toolbar toolbar;
    CollapsingToolbarLayout coll_toolbar;
    ImageView trailImage_iv;
    TextView trailName_tv;
    TextView trailGrade_tv;
    ImageView trailGrade_im;
    TextView trailDistance_tv;
    TextView trailCompleteTime_tv;
    TextView trailAbout_tv;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    LocalAnimalDatabase db = null;
    HistoryViewModel historyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_trail_detail);
        db = LocalAnimalDatabase.getInstance(this);
        toolbar = findViewById(R.id.trail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        trailImage_iv = findViewById(R.id.expandedImage);
        trailName_tv = findViewById(R.id.scrolling_trail_name_tv);
        trailGrade_tv = findViewById(R.id.scrolling_grade_tv);
        trailGrade_im = findViewById(R.id.scrolling_grade_iv);
        trailDistance_tv = findViewById(R.id.scrolling_distance_tv);
        trailCompleteTime_tv = findViewById(R.id.scrolling_completeTime_tv);
        trailAbout_tv = findViewById(R.id.scrolling_about_tv);
        extendedFloatingActionButton = findViewById(R.id.startTrail_btn);

        Bundle bundle = getIntent().getExtras();
        trail = bundle.getParcelable("trail");
        trailName = trail.getTrail_name();
        trailImage = trail.getTrail_image();
        trailGrade = trail.getDifficulty_name();
        trailGradeImage = trail.getDifficulty_image();
        trailDistance = trail.getDistance();
        trailCompleteTime = trail.getComplete_time();
        trailAbout = trail.getDescription();

        trailName_tv.setText(trailName);
        trailGrade_tv.setText(trailGrade);
        trailDistance_tv.setText(trailDistance);
        trailCompleteTime_tv.setText(trailCompleteTime);
        trailAbout_tv.setText(trailAbout);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        coll_toolbar = findViewById(R.id.collapsing_toolbar);
        coll_toolbar.setTitle("");
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        coll_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        coll_toolbar.setContentScrimColor(this.getResources().getColor(colorPrimary));
        Glide.with(this)
                .load(trailImage)//searchResults.get(position).getImageUrl())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(trailImage_iv);

        Glide.with(this)
                .load(trailGradeImage)//searchResults.get(position).getImageUrl())
                .placeholder(new ColorDrawable(Color.BLACK))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(trailGrade_im);


        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.initializeVars(getApplication());

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TrailDetailActivity.this,SpottingAnimalActivity.class);
//                Bundle trailBundle = new Bundle();
//                trailBundle.putParcelable("trail",trail);
//                intent.putExtras(trailBundle);
//                Date date = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String dateNowStr = sdf.format(date);
//                History history = new History(trail.getTrail_name(),0,dateNowStr);
//                historyViewModel.insert(history);
//                startActivity(intent);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateNowStr = sdf.format(date);
                History history = new History(trail.getTrail_name(),0,dateNowStr);

                InsertHis insertHis = new InsertHis();
                insertHis.execute(history);


            }
        });


    }

    private class InsertHis extends AsyncTask<History,Void,Void>{
        @Override
        protected Void doInBackground(History... histories) {
            db.historyDAO().insert(histories[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Intent intent = new Intent(TrailDetailActivity.this,SpottingAnimalActivity.class);
            Bundle trailBundle = new Bundle();
            trailBundle.putParcelable("trail",trail);
            intent.putExtras(trailBundle);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}