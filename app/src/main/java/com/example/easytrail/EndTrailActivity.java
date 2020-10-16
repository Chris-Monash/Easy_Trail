package com.example.easytrail;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class EndTrailActivity extends AppCompatActivity {
    TextView animalCount_tv;
    TextView animalScore_tv;
    TextView trailName_tv;
    TextView motivation_tv;
    MaterialButton animalInfo_btn;
    MaterialButton exit_btn;
    LocalAnimalDatabase db = null;
    Toolbar toolbar;
    private List<LocalAnimal> localAnimals;
    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());


//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |View.SYSTEM_UI_FLAG_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        StatusBar.setActivityAdapter(this,false);





        setContentView(R.layout.activity_end_trail);
        toolbar = findViewById(R.id.endTrail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        animalCount_tv = findViewById(R.id.endTrail_animalCount_tv);
        animalScore_tv = findViewById(R.id.endTrail_animalScore_tv);
        trailName_tv = findViewById(R.id.endTrail_trailName_tv);
        motivation_tv = findViewById(R.id.endTrail_motivation_tv);
        animalInfo_btn = findViewById(R.id.endTrail_animalInfo_btn);
        exit_btn = findViewById(R.id.endTrial_exit_btn);
        db = LocalAnimalDatabase.getInstance(this);
        GetLatestTrail getLatestTrail = new GetLatestTrail();
        getLatestTrail.execute();
        animalInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndTrailActivity.this, AnimalInfoActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(EndTrailActivity.this).toBundle());
            }
        });


    }

    private class GetLatestTrail extends AsyncTask<Void,Void,History>{
        @Override
        protected History doInBackground(Void... voids) {
            history = db.historyDAO().getNewestOne();
            return history;
        }

        @Override
        protected void onPostExecute(History history) {
            GetLocalAnimalInfo getLocalAnimalInfo = new GetLocalAnimalInfo();
            getLocalAnimalInfo.execute(String.valueOf(history.getHistory_id()),history.getCreated_date());
        }
    }

    private class GetLocalAnimalInfo extends AsyncTask<String,Void,List<LocalAnimal>>{
        @Override
        protected List<LocalAnimal> doInBackground(String... strings) {
            localAnimals = db.localAnimalDAO().findAnimalForHistory(Integer.parseInt(strings[0]),strings[1]);
            return  localAnimals;
        }

        @Override
        protected void onPostExecute(List<LocalAnimal> localAnimals) {
            animalCount_tv.setText(String.valueOf(localAnimals.size()));
            animalScore_tv.setText(String.valueOf(history.getCurrent_score()));
            trailName_tv.setText(history.getTrail_name());
            if (localAnimals.size() == 0){
                motivation_tv.setText("Oops! Seems like you missed animals. Good luck with your next trail");
            }
        }
    }

    //following code refers to https://stackoverflow.com/questions/8430805/clicking-the-back-button-twice-to-exit-an-activity
    boolean doubleBackToExitPressedOnce = false;
    Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(EndTrailActivity.this,MainActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(EndTrailActivity.this).toBundle());
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to home page", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }
}