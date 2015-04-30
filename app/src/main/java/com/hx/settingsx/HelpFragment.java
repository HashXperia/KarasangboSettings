package com.hx.settingsx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class HelpFragment extends Fragment {

    public HelpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        final CardView card5 = (CardView) rootView.findViewById(R.id.c5);
        final CardView card6 = (CardView) rootView.findViewById(R.id.c6);

        if(SettingsFragment.theme==1) {
            card5.setCardBackgroundColor(0xff424242);
            card6.setCardBackgroundColor(0xff424242);
        }

        RelativeLayout rating =(RelativeLayout) rootView.findViewById(R.id.rate);
        rating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.hx.settingsx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
