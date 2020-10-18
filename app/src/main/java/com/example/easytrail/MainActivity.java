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
//        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setLightMode(this);
        StatusBar.setActivityAdapter(this,true);


//        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
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
                        StatusBar.setActivityAdapter(MainActivity.this,true);
//                        toolbar.setTitle("Trail");

                        break;
                    case R.id.navigation_explore:
                        fragment = new ExploreFragment();
//                        StatusBarUtil.setLightMode(MainActivity.this);
                        StatusBar.setActivityAdapter(MainActivity.this,true);
//                        toolbar.setTitle("Explore");

                        break;
                    case R.id.navigation_help:

                        fragment = new HelpFragment();
//                        StatusBarUtil.setDarkMode(MainActivity.this);
//                        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this,null);


                        StatusBar.setActivityAdapter(MainActivity.this,false);
//                        StatusBar.setFragmentAdapter(fragment,MainActivity.this.getWindow().findViewById(R.id.content), true);
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
//    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
//        Window win = activity.getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }
//        private void showCustomUI() {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//}



}