package com.example.easytrail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.easytrail.adapter.OnBoardingAdapter;
import com.example.easytrail.model.OnBoardingItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private OnBoardingAdapter adapter;
    private List<OnBoardingItem> onBoardingItems;
    LinearLayout layoutOnBoardingIndicators;
    ViewPager2 viewPager2;
    MaterialButton next_start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences userInfo = getSharedPreferences("start",MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        int boardingTime;
        boardingTime = userInfo.getInt("start",1);
        if (boardingTime == 1){
            boardingTime ++;
            editor.putInt("start", boardingTime);
            editor.commit();
            setContentView(R.layout.activity_splash);
            layoutOnBoardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
            next_start_btn = findViewById(R.id.buttonOnBoardingAction);
            setupOnBoardingItems();
            viewPager2 = findViewById(R.id.onboardingViewPager);
            viewPager2.setAdapter(adapter);
            setupOnBoardingIndicators();
            setCurrentOnBoardingIndicator(0);
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentOnBoardingIndicator(position);
                }
            });

            next_start_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager2.getCurrentItem() < adapter.getItemCount()-1){
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                    }else{
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }else {
            boardingTime ++;
            editor.putInt("start", boardingTime);
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//    }
    private void setupOnBoardingItems(){

        onBoardingItems = new ArrayList<OnBoardingItem>();

        OnBoardingItem itemWelcome = new OnBoardingItem();
        itemWelcome.setTitle("Welcome");
        itemWelcome.setDescription("Enjoy outdoor hikes in Mornington Peninsula, while" +
            " getting to know more about the animals you encounter on your hike.");
        itemWelcome.setImage(R.drawable.slide_one_img);

        OnBoardingItem itemWheelchair = new OnBoardingItem();
        itemWheelchair.setTitle("Disability no more a barrier");
        itemWheelchair.setDescription(" All the trails shown are grade 1, hence can easily be completed" +
            " by both wheelchair bounded and regular public.");
        itemWheelchair.setImage(R.drawable.second);

        OnBoardingItem itemSpot = new OnBoardingItem();
        itemSpot.setTitle("Spotting the animals!");
        itemSpot.setDescription(" While cruising through your trail you will be shown animals that you" +
            " might spot on your particular trail. As soon as you spot an animal click on the animal " +
            " picture immediately to receive points.");
        itemSpot.setImage(R.drawable.third);



        onBoardingItems.add(itemWelcome);
        onBoardingItems.add(itemWheelchair);
        onBoardingItems.add(itemSpot);
        adapter = new OnBoardingAdapter(this,onBoardingItems);



    }

    private void setupOnBoardingIndicators(){
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i< indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }


    private void setCurrentOnBoardingIndicator(int index) {
        int childCount = layoutOnBoardingIndicators.getChildCount();

        for (int i =0; i < childCount; i++){
            ImageView imageView = (ImageView)layoutOnBoardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }

        if(index == adapter.getItemCount() - 1){
            next_start_btn.setText("Start");
        }else {
            next_start_btn.setText("Next");
        }
    }

}