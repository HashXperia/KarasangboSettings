package com.hx.settingsx;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class SettingsFragment extends Fragment {

    static int choice = -1;
    static int theme;
    private static Context context;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        final Spinner spinner =(Spinner) rootView.findViewById(R.id.spinner1);
        final CheckBox themed = (CheckBox) rootView.findViewById(R.id.dark_ed);
        final CardView card3 = (CardView) rootView.findViewById(R.id.c3);
        final CardView card4 = (CardView) rootView.findViewById(R.id.c4);

        ArrayAdapter<CharSequence> adp3= ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_items, android.R.layout.simple_list_item_1);

        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String ss=spinner.getSelectedItem().toString();
                SharedPreferences sharedPreferences;
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                switch (ss) {
                    case "Main page":
                        editor.putInt("choice", -1);
                        break;
                    case "General":
                        editor.putInt("choice", 0);
                        break;
                    case "Advanced":
                        editor.putInt("choice", 1);
                        break;
                    case "Device Info":
                        editor.putInt("choice", 2);
                        break;
                    case "Settings":
                        editor.putInt("choice", 3);
                        break;
                    case "Help and Feedback":
                        editor.putInt("choice", 4);
                        break;
                    default:
                        break;
                }
                editor.commit();

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        themed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences sharedPreferences;
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(themed.isChecked())
                {
                    editor.putInt("theme", 1);
                    theme=1;
                }
                else
                {
                    editor.putInt("theme", 0);
                    theme=0;
                }
                editor.commit();
                if(MainActivity.rt!=theme) {
                    Intent intent = new Intent(SettingsFragment.this.getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });

        if (theme==1) {
            themed.setChecked(true);
            spinner.setPopupBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff424242")));
            card3.setCardBackgroundColor(0xff424242);
            card4.setCardBackgroundColor(0xff424242);
        }

        return rootView;
    }
}
