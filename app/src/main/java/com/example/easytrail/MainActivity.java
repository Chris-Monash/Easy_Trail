package com.example.easytrail;

import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.easytrail.ui.explore.ExploreFragment;
import com.example.easytrail.ui.help.HelpFragment;
import com.example.easytrail.ui.trail.TrailFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_main);

        StatusBarUtil.setLightMode(this);


        StatusBarUtil.setTranslucentForImageViewInFragment(this,0,null);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        bottomNav = findViewById(R.id.nav_view);
        if (savedInstanceState == null){
            bottomNav.setItemSelected(R.id.navigation_trail,true);
            fragmentManager = getSupportFragmentManager();
            TrailFragment trailFragment = new TrailFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, trailFragment)
                    .commit();

        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.navigation_trail:
                        fragment = new TrailFragment();
//                        StatusBarUtil.setLightMode(MainActivity.this);
//                        StatusBar.setActivityAdapter(MainActivity.this,true);
//                        toolbar.setTitle("Trail");

                        break;
                    case R.id.navigation_explore:
                        fragment = new ExploreFragment();
//                        StatusBarUtil.setLightMode(MainActivity.this);
//                        StatusBar.setActivityAdapter(MainActivity.this,true);
//                        toolbar.setTitle("Explore");

                        break;
                    case R.id.navigation_help:

                        fragment = new HelpFragment();
//                        StatusBarUtil.setDarkMode(MainActivity.this);
//                        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this,null);

                        //StatusBar.setActivityAdapter(MainActivity.this,false);

//                        toolbar.setTitle("Notification");

                        break;
                }
                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }else{
                    Log.e(TAG,"Error in creating fragment");
                }
                }




        });


    }



}