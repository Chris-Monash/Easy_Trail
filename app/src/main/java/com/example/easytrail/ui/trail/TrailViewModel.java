package com.example.easytrail.ui.trail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrailViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is trail fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}