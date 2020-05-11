package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.roadworks;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ahmad_afaq_s1625481.R;
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Roadwork;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class RoadworksFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private List<Roadwork> roadworks;
    private List<Roadwork> roadworksPlanned;
    private RoadworksAdapter roadworksAdapter;
    private ToggleButton selectionButton;


    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RoadworksViewModel roadworksViewModel = ViewModelProviders.of(this).get(RoadworksViewModel.class);
        View root = inflater.inflate(R.layout.roadworks_main, container, false);

        ListView roadworkList = root.findViewById(R.id.roadworks_list);
        selectionButton = root.findViewById(R.id.roadworks_button);
        Button dateButton = root.findViewById(R.id.roadword_date_button);
        EditText searchInput = root.findViewById(R.id.roadworks_search);

        this.roadworks = new ArrayList<>();
        this.roadworksPlanned = new ArrayList<>();

        roadworksAdapter = new RoadworksAdapter(root.getContext(), new ArrayList<>());
        roadworkList.setAdapter(roadworksAdapter);

        roadworksViewModel.getRoadworks().observe(this, roadworks -> {
            RoadworksFragment.this.roadworks = roadworks;
            RoadworksFragment.this.roadworksAdapter.addAll(roadworks);
            RoadworksFragment.this.roadworksAdapter.notifyDataSetChanged();

        });

        roadworksViewModel.getPlannedRoadworks().observe(this, planned -> {
            RoadworksFragment.this.roadworksPlanned = planned;
            RoadworksFragment.this.roadworksAdapter.addAll(planned);
            RoadworksFragment.this.roadworksAdapter.notifyDataSetChanged();

        });

        selectionButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    roadworksAdapter.clear();
                    roadworksAdapter.addAll(roadworksPlanned);
                    selectionButton.setTextColor(Color.RED);
                }else {
                    roadworksAdapter.clear();
                    roadworksAdapter.addAll(roadworks);
                    selectionButton.setTextColor(Color.BLUE);
                }
                roadworksAdapter.notifyDataSetChanged();
            }

        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
               dialogFragment.setTargetFragment(RoadworksFragment.this, 0);
               dialogFragment.show(getFragmentManager().beginTransaction(), "date-picked");


            }
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RoadworksFragment.this.roadworksAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return root;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle bundle = data.getExtras();
        Calendar cal = (Calendar) bundle.get("date-picked");
        this.filterByDate(cal);
        Log.d("alert", cal.getTime().toString());
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void filterByDate(Calendar date){
        for(int i = 0; i < roadworksAdapter.getCount(); i++){
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(roadworksAdapter.getItem(i).getDate());
            if (date.before(calendar)){
                roadworksAdapter.remove(roadworksAdapter.getItem(i));
            }
        roadworksAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
