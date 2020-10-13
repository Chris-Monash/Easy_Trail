package com.example.easytrail;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;


import com.example.easytrail.ui.dashboard.DashboardFragment;
import com.example.easytrail.ui.trail.TrailFragment;
import com.example.easytrail.ui.notifications.NotificationsFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Easy Trail");
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
//                        toolbar.setTitle("Trail");
                        break;
                    case R.id.navigation_dashboard:
                        fragment = new DashboardFragment();
//                        toolbar.setTitle("Dashboard");
                        break;
                    case R.id.navigation_notifications:
                        fragment = new NotificationsFragment();
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


//        BottomNavigationView navView = findViewById(R.id.nav_view);
//
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    });
    }

}