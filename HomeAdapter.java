package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.home;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ahmad_afaq_s1625481.R;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Incident;

import java.text.SimpleDateFormat;
import java.util.List;

class HomeAdapter extends ArrayAdapter<Incident> {
    public HomeAdapter(@NonNull Context context, List<Incident> inc) {
        super(context, 0, inc);
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        Incident incident = getItem(position);
        if (convert == null) {
            convert = LayoutInflater.from(getContext()).inflate(R.layout.incidents_item, parent, false);
        }
        TextView incidentTitle = convert.findViewById(R.id.incident_title);
        TextView incidentDate = convert.findViewById(R.id.incident_date);
        TextView incidentLink = convert.findViewById(R.id.incident_link);
        TextView incidentDescription = convert.findViewById(R.id.incident_description);


        incidentTitle.setText(incident.getTitle());
        incidentDescription.setText(incident.getDesc());
        incidentLink.setText("Additional information: " + incident.getIncidentLink());
        incidentDate.setText("Created on: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(incident.getDate()));

        return convert;
    }
}
