package io.geeteshk.settingsx;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;

public class GeneralFragment extends Fragment {

    private int vibratorLevel = -1;
    private String vibratorPath = "";

    public GeneralFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general, container, false);

        final SeekBar vibratorBar = (SeekBar) rootView.findViewById(R.id.vibrator_bar);
        final TextView vibratorValue = (TextView) rootView.findViewById(R.id.vibrator_value);

        if (new File(Constants.VIBRATOR_LEVEL).exists()) {
            vibratorLevel = 0; // TODO: Set this value later
            vibratorPath = Constants.VIBRATOR_LEVEL;
        } else if (new File(Constants.VIBRATOR_LEVEL_Z3).exists()) {
            vibratorLevel = 0; // TODO: Set this one too
            vibratorPath = Constants.VIBRATOR_LEVEL_Z3;
        }

        if (vibratorLevel == -1) {
            vibratorBar.setEnabled(false);
            vibratorValue.setText("-");
            vibratorValue.setTextColor(getActivity().getResources().getColor(android.R.color.darker_gray));
        } else {
            vibratorBar.setProgress(vibratorLevel);
            vibratorValue.setText(String.valueOf(vibratorLevel));
        }

        vibratorBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vibratorValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (vibratorLevel != -1) {
                    // TODO: Find a way to set value
                }

                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
            }
        });

        return rootView;
    }
}
