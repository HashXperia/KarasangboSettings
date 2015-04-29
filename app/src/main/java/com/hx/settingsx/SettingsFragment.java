package com.hx.settingsx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    static int choice = -1;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        final Spinner spinner =(Spinner) rootView.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adp3= ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_items, android.R.layout.simple_list_item_1);

        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String ss=spinner.getSelectedItem().toString();
                switch (ss) {
                    case "General":
                        choice=0;
                        break;
                    case "Advanced":
                        choice=1;
                        break;
                    case "Device Info":
                        choice=2;
                        break;
                    case "Settings":
                        choice=3;
                        break;
                    case "Help and Feedback":
                        choice=4;
                        break;
                    default:
                        break;
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        return rootView;
    }
}
