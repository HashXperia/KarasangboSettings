package com.hx.settingsx;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class GeneralFragment extends Fragment {

    int vibratorLevel = -1;
    String vibratorPath = "";
    int dt2w_val = 0;
    String dt2wPath = "";
    int levelvib;

    public GeneralFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general, container, false);

        final SeekBar vibratorBar = (SeekBar) rootView.findViewById(R.id.vibrator_bar);
        final TextView vibratorValue = (TextView) rootView.findViewById(R.id.vibrator_value);
        final CheckBox dt2w = (CheckBox) rootView.findViewById(R.id.dt2w_ed);
        final CardView card1 = (CardView) rootView.findViewById(R.id.c1);
        final CardView card2 = (CardView) rootView.findViewById(R.id.c2);

        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(SettingsFragment.theme==1) {
            card1.setCardBackgroundColor(0xff424242);
            card2.setCardBackgroundColor(0xff424242);
        }

        if (new File(Constants.VIBRATOR_LEVEL).exists()) {
            vibratorLevel = Integer.valueOf(Utils.readOneLine(Constants.VIBRATOR_LEVEL));
            vibratorPath = Constants.VIBRATOR_LEVEL;
        } else if (new File(Constants.VIBRATOR_LEVEL_VTG).exists()) {
            vibratorLevel = Integer.valueOf(Utils.readOneLine(Constants.VIBRATOR_LEVEL_VTG));
            vibratorPath = Constants.VIBRATOR_LEVEL_VTG;
        }

        if (new File(Constants.DT2W).exists()) {
            dt2wPath = Constants.DT2W;
            dt2w_val = Integer.valueOf(Utils.readOneLine(dt2wPath));
            if(dt2w_val == 1) {
                dt2w.setChecked(true);
            }
        } else {
            dt2w_val = -1;
        }

        if (vibratorLevel == -1) {
            vibratorBar.setEnabled(false);
            vibratorValue.setText("-");
            vibratorValue.setTextColor(getActivity().getResources().getColor(android.R.color.background_light));
        } else {
            levelvib = sharedPreferences.getInt("viblevel", vibratorLevel);
            vibratorBar.setProgress(levelvib);
            vibratorValue.setText(String.valueOf(levelvib));
        }

        vibratorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences sharedPreferences;
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                vibratorValue.setText(String.valueOf(progress));
                if (vibratorLevel != -1) {
                    if (vibratorPath.equals(Constants.VIBRATOR_LEVEL)) {
                        Utils.writeValue(vibratorPath, String.valueOf(seekBar.getProgress()));
                        editor.putInt("viblevel", seekBar.getProgress());
                        editor.commit();
                    } else if (vibratorPath.equals(Constants.VIBRATOR_LEVEL_VTG)) {
                        Utils.writeValue(vibratorPath, String.valueOf(getVtgLevel(seekBar.getProgress())));
                        editor.putInt("viblevel", seekBar.getProgress());
                        editor.commit();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(420);
            }
        });

        dt2w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dt2w_val == -1) {
                    dt2w.setChecked(false);
                    Toast.makeText(getActivity(), "This feature is not supported by kernel", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.writeValue(dt2wPath, dt2w.isChecked() ? "1" : "0");
                }
            }
        });

        return rootView;
    }

    private int getVtgLevel(int percentage) {
        // TODO: This function is temporary until we learn more about vtg_level
        return ((19 * percentage) / 100) + 12;
    }
}
