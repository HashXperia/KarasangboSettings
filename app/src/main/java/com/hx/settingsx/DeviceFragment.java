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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                    infoTitle.setText("Build ID");
                    infoText.setText(Build.ID);
                    break;
                case 8:
                    infoTitle.setText("Manufacturer");
                    infoText.setText(Build.MANUFACTURER);
                    break;
                case 9:
                    infoTitle.setText("Model");
                    infoText.setText(Build.MODEL);
                    break;
                case 10:
                    infoTitle.setText("Product");
                    infoText.setText(Build.PRODUCT);
                    break;
                case 11:
                    infoTitle.setText("Radio");
                    if (Build.VERSION.SDK_INT < 14) {
                        infoText.setText(Build.RADIO);
                    } else {
                        infoText.setText(Build.getRadioVersion());
                    }
                    break;
                case 12:
                    infoTitle.setText("Serial");
                    infoText.setText(Build.SERIAL);
                    break;
                case 13:
                    if (Build.VERSION.SDK_INT >= 21) {
                        infoTitle.setText("32bit ABIs");
                        infoText.setText(Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
                    } else {
                        infoTitle.setText("CPU ABI");
                        infoText.setText(Build.CPU_ABI);
                    }
                    break;
                case 14:
                    if (Build.VERSION.SDK_INT >= 21) {
                        infoTitle.setText("64bit ABIs");
                        infoText.setText(Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
                    } else {
                        infoTitle.setText("CPU ABI2");
                        infoText.setText(Build.CPU_ABI2);
                    }
                    break;
                case 15:
                    infoTitle.setText("Build Type");
                    infoText.setText(Build.TYPE);
                    break;
                case 16:
                    infoTitle.setText("SELinux Status");
                    Process process = null;
                    try {
                        process = Runtime.getRuntime().exec("getenforce");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    InputStream slnx0=process.getInputStream();
                    InputStreamReader slnx1 = new InputStreamReader(slnx0);
                    StringBuilder slnx=new StringBuilder();
                    BufferedReader slnx2 = new BufferedReader(slnx1);
                    String sb = null;
                    try {
                        sb = slnx2.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    while(sb != null) {
                        slnx.append(sb);
                        try {
                            sb =slnx2.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    infoText.setText(slnx.toString());
                    break;
            }

            return rootView;
        }

        @Override
        public int getCount() {
            return 17;
        }
    }
}
