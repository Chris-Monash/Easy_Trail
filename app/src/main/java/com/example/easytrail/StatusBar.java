package com.example.easytrail;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

// this class refers to website resource. Link: https://juejin.im/post/6844903975980908558#heading-23
public class StatusBar {
    public static void setActivityAdapter(Activity activity, boolean isSetLightStatusBar) {
 //aim to android 4.4 - 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
 // android 5.0 and above
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

 /*
 android 6.0 set the status bar to dark colour
 */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isSetLightStatusBar) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                }

            }

        }
    }


    public static void setFragmentAdapter(Fragment fragment, View rootView, boolean headerIsImage) {
        //aim to android 4.4 - 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (!headerIsImage) {
                int resourceId = fragment.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
                int height = fragment.getActivity().getResources().getDimensionPixelSize(resourceId);
                rootView.setPadding(0, height, 0, 0);
            }
        }
        // android 5.0 and above
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if (!headerIsImage) {
                int resourceId = fragment.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
                int height = fragment.getActivity().getResources().getDimensionPixelSize(resourceId);
                rootView.setPadding(0, height, 0, 0);
            }
        }
    }





}
