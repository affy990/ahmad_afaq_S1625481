package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.map;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class MapViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}