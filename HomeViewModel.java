package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.home;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Incident;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.Async;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.HttpRequest;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.IncidentParser;

import java.util.ArrayList;
import java.util.List;

class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Incident>> incidents;

    public HomeViewModel() {
        incidents = new MutableLiveData<>();

        new HttpRequest("https://trafficscotland.org/rss/feeds/currentincidents.aspx", new IncidentParser(),
                new Async() {
                    @Override
                    public void processFinish(ArrayList output) {
                        List<Incident> inc = (List<Incident>) output;
                        incidents.setValue(inc);
                    }
                }

        ).execute();

    }

    public MutableLiveData<List<Incident>> getAllIncidents() {
        return incidents;
    }
}