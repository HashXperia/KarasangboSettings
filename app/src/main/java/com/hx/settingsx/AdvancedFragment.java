package com.hx.settingsx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

public class AdvancedFragment extends Fragment {

    public AdvancedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_advanced, container, false);
        final CardView card7 = (CardView) rootView.findViewById(R.id.c7);

        if(SettingsFragment.theme==1) {
            card7.setCardBackgroundColor(0xff424242);
        }

        RelativeLayout logcat =(RelativeLayout) rootView.findViewById(R.id.log);
        logcat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Runtime runtime = Runtime.getRuntime();
                try {
                    Runtime.getRuntime().exec("logcat -t 20000 -f" + " /sdcard/logcat.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Logcat saved in /sdcard/logcat.txt", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
}
