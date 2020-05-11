package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.roadworks;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Roadwork;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.Async;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.HttpRequest;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.RoadworksParser;

import java.util.ArrayList;
import java.util.List;


class RoadworksViewModel extends ViewModel {
    private MutableLiveData<List<Roadwork>> roadworks;
    private MutableLiveData<List<Roadwork>> roadworksPlanned;


    public RoadworksViewModel() {
        roadworks = new MutableLiveData<>();
        new HttpRequest("https://trafficscotland.org/rss/feeds/roadworks.aspx", new RoadworksParser(), new Async() {
            @Override
            public void processFinish(ArrayList output) {
                List<Roadwork> rds = (List<Roadwork>) output;
                roadworks.setValue(rds);
            }
        }).execute();

    roadworksPlanned = new MutableLiveData<>();
    new HttpRequest("https://trafficscotland.org/rss/feeds/plannedroadworks.aspx", new RoadworksParser(), new Async() {
            @Override
            public void processFinish(ArrayList output) {
                List<Roadwork> rds = (List<Roadwork>) output;
                roadworksPlanned.setValue(rds);
            }
        }).execute();
    }




    public MutableLiveData<List<Roadwork>> getText() {
        return this.roadworks;
    }
    public LiveData<List<Roadwork>> getRoadworks() {
        return this.roadworks;
    }
    public LiveData<List<Roadwork>> getPlannedRoadworks() {
        return this.roadworksPlanned;
    }


}
