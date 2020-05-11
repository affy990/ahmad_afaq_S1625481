package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.ui.home;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ahmad_afaq_s1625481.R;

import java.util.Calendar;

class HomeFragment extends Fragment {

    private HomeAdapter adapter;
    private ListView incidentsList;
    private Button filterDateButton;


    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        incidentsList = root.findViewById(R.id.incidents_list);
        homeViewModel.getAllIncidents().observe(this, incidents -> {
            adapter = new HomeAdapter(root.getContext(), incidents);
            incidentsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

        EditText searchInput = root.findViewById(R.id.search_indicents);
        filterDateButton = root.findViewById(R.id.dateButton_incidents);


        filterDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragmentIncident picker = new DatePickerFragmentIncident();
                picker.setTargetFragment(HomeFragment.this, 0);
                picker.show(getFragmentManager().beginTransaction(), "date-picked-incident");
            }
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HomeFragment.this.adapter.getFilter().filter(s);
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
        Calendar calendar = (Calendar) bundle.get("date-picked-incident");
        this.filterByDate(calendar);
        this.filterDateButton.setText("Set to: " + calendar.getTime().toString());
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void filterByDate(Calendar date){
        for(int i = 0; i < adapter.getCount(); i++){
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(adapter.getItem(i).getDate());
            if(date.before(calendar)){
                adapter.remove(adapter.getItem(i));
            }
            adapter.notifyDataSetChanged();
        }
    }
}
