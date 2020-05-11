package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.roadworks;



import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import androidx.annotation.NonNull;

import com.example.ahmad_afaq_s1625481.R;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Roadwork;

import java.util.List;

class RoadworksAdapter extends ArrayAdapter<Roadwork> {
    public RoadworksAdapter(@NonNull Context context, List<Roadwork> roadworks) {
        super(context,0, roadworks);
    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent){
        Roadwork roadwork = getItem(pos);

        if (convert == null) {
            convert = LayoutInflater.from(getContext()).inflate(R.layout.roadwords_item, parent, false);
        }

        TextView title = convert.findViewById(R.id.roadwork_title);
        TextView desc = convert.findViewById(R.id.roadwork_desc);


        title.setText(roadwork.getTitle());
        desc.setText(Html.fromHtml(roadwork.getDesc()));


        return convert;
    }


}
