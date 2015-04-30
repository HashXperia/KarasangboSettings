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
        final CardView card9 = (CardView) rootView.findViewById(R.id.c9);
        final CardView card10 = (CardView) rootView.findViewById(R.id.c10);

        if(SettingsFragment.theme==1) {
            card7.setCardBackgroundColor(0xff424242);
            card9.setCardBackgroundColor(0xff424242);
            card10.setCardBackgroundColor(0xff424242);
        }

        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }

        RelativeLayout logcat =(RelativeLayout) rootView.findViewById(R.id.log);
        logcat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Runtime.getRuntime().exec("logcat -d -f /sdcard/logcat.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Logcat saved in /sdcard/logcat.txt", Toast.LENGTH_LONG).show();
            }
        });

        RelativeLayout rlog =(RelativeLayout) rootView.findViewById(R.id.rlog);
        rlog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Runtime.getRuntime().exec("su -c logcat -d -b radio -f /sdcard/radiolog.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Radio log saved in /sdcard/radiolog.txt", Toast.LENGTH_LONG).show();
            }
        });

        RelativeLayout dmesg =(RelativeLayout) rootView.findViewById(R.id.dmesg);
        dmesg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Runtime.getRuntime().exec("su -c dmesg > /sdcard/dmesg.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Dmesg saved in /sdcard/dmesg.txt", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
}
