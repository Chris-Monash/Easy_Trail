package com.example.easytrail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.easytrail.model.TrailResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;

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
    Toolbar toolbar;
    CollapsingToolbarLayout coll_toolbar;
    ImageView trailImage_iv;
    TextView trailName_tv;
    TextView trailGrade_tv;
    ImageView trailGrade_im;
    TextView trailDistance_tv;
    TextView trailCompleteTime_tv;
    TextView trailAbout_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_detail);
        toolbar = findViewById(R.id.trail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        trailImage_iv = findViewById(R.id.expandedImage);
        trailName_tv = findViewById(R.id.scrolling_trail_name_tv);
        trailGrade_tv = findViewById(R.id.scrolling_grade_tv);
        trailGrade_im = findViewById(R.id.scrolling_grade_iv);
        trailDistance_tv = findViewById(R.id.scrolling_distance_tv);
        trailCompleteTime_tv = findViewById(R.id.scrolling_completeTime_tv);
        trailAbout_tv = findViewById(R.id.scrolling_about_tv);

        Bundle bundle = getIntent().getExtras();
        TrailResult trail = bundle.getParcelable("trail");
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

    }
}