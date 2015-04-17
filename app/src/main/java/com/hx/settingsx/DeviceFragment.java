package com.hx.settingsx;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class DeviceFragment extends Fragment {

    public DeviceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device, container, false);

        ListView infoList = (ListView) rootView.findViewById(R.id.info_list);
        infoList.setAdapter(new InfoAdapter(getActivity(), R.layout.info_list_item));

        return rootView;
    }

    private class InfoAdapter extends ArrayAdapter<String> {

        Context mContext;
        int mResource;

        public InfoAdapter(Context context, int resource) {
            super(context, resource);
            mContext = context;
            mResource = resource;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView;

            if (convertView != null) {
                rootView = convertView;
            } else {
                rootView = inflater.inflate(R.layout.info_list_item, parent, false);
            }

            TextView infoTitle = (TextView) rootView.findViewById(R.id.info_title);
            TextView infoText = (TextView) rootView.findViewById(R.id.info_text);

            switch (position) {
                case 0:
                    infoTitle.setText("Board");
                    infoText.setText(Build.BOARD);
                    break;
                case 1:
                    infoTitle.setText("Bootloader");
                    infoText.setText(Build.BOOTLOADER);
                    break;
                case 2:
                    infoTitle.setText("Brand");
                    infoText.setText(Build.BRAND);
                    break;
                case 3:
                    infoTitle.setText("Device");
                    infoText.setText(Build.DEVICE);
                    break;
                case 4:
                    infoTitle.setText("Display");
                    infoText.setText(Build.DISPLAY);
                    break;
                case 5:
                    infoTitle.setText("Fingerprint");
                    infoText.setText(Build.FINGERPRINT);
                    break;
                case 6:
                    infoTitle.setText("Hardware");
                    infoText.setText(Build.HARDWARE);
                    break;
                case 7:
                    infoTitle.setText("Host");
                    infoText.setText(Build.HOST);
                    break;
                case 8:
                    infoTitle.setText("ID");
                    infoText.setText(Build.ID);
                    break;
                case 9:
                    infoTitle.setText("Manufacturer");
                    infoText.setText(Build.MANUFACTURER);
                    break;
                case 10:
                    infoTitle.setText("Model");
                    infoText.setText(Build.MODEL);
                    break;
                case 11:
                    infoTitle.setText("Product");
                    infoText.setText(Build.PRODUCT);
                    break;
                case 12:
                    infoTitle.setText("Radio");
                    if (Build.VERSION.SDK_INT < 14) {
                        infoText.setText(Build.RADIO);
                    } else {
                        infoText.setText(Build.getRadioVersion());
                    }
                    break;
                case 13:
                    infoTitle.setText("Serial");
                    infoText.setText(Build.SERIAL);
                    break;
                case 14:
                    if (Build.VERSION.SDK_INT >= 21) {
                        infoTitle.setText("32bit ABIs");
                        infoText.setText(Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
                    } else {
                        infoTitle.setText("CPU ABI");
                        infoText.setText(Build.CPU_ABI);
                    }
                    break;
                case 15:
                    if (Build.VERSION.SDK_INT >= 21) {
                        infoTitle.setText("64bit ABIs");
                        infoText.setText(Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
                    } else {
                        infoTitle.setText("CPU ABI2");
                        infoText.setText(Build.CPU_ABI2);
                    }
                    break;
                case 16:
                    infoTitle.setText("Tags");
                    infoText.setText(Build.TAGS);
                    break;
                case 17:
                    infoTitle.setText("Time");
                    infoText.setText(String.valueOf(Build.TIME));
                    break;
                case 18:
                    infoTitle.setText("Type");
                    infoText.setText(Build.TYPE);
                    break;
                case 19:
                    infoTitle.setText("User");
                    infoText.setText(Build.USER);
                    break;
            }

            return rootView;
        }

        @Override
        public int getCount() {
            return 20;
        }
    }
}
