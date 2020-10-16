package com.example.easytrail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easytrail.adapter.AnimalInfoRecyclerViewAdapter;
import com.example.easytrail.database.LocalAnimalDatabase;
import com.example.easytrail.entity.History;
import com.example.easytrail.entity.LocalAnimal;

import java.util.ArrayList;
import java.util.List;

public class AnimalInfoActivity extends AppCompatActivity {
    private List<LocalAnimal> localAnimals;
    private History history;
    private AnimalInfoRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    Toolbar toolbar;
    LocalAnimalDatabase db = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        StatusBar.setActivityAdapter(this,true);
        setContentView(R.layout.activity_animal_info);
        toolbar = findViewById(R.id.animalInfo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        db = LocalAnimalDatabase.getInstance(this);
        progressBar = findViewById(R.id.animalInfo_progressbar);
        recyclerView = findViewById(R.id.animalInfo_recyclerView);
        localAnimals = new ArrayList<LocalAnimal>();
        adapter = new AnimalInfoRecyclerViewAdapter(this, localAnimals);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GetLatestTrail getLatestTrail = new GetLatestTrail();
        getLatestTrail.execute();
    }

    private class GetLatestTrail extends AsyncTask<Void,Void, History>{
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
            adapter.addLocalAnimals(localAnimals);
            if (recyclerView.getItemDecorationCount() == 0) {
                recyclerView.addItemDecoration(new DividerItemDecoration(AnimalInfoActivity.this, LinearLayoutManager.VERTICAL));
            }
            recyclerView.setAdapter(adapter);
            layoutManager = new LinearLayoutManager(AnimalInfoActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            progressBar.setVisibility(View.GONE);
        }
    }

}