package io.geeteshk.settingsx;

import android.annotation.SuppressLint;
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

    @SuppressWarnings("deprecation")
    private class InfoAdapter extends ArrayAdapter<String> {

        Context mContext;
        int mResource;

        String[] mTitles = {"Board", "Bootloader", "Brand", "Device", "Display", "Fingerprint", "Hardware", "Host", "ID", "Manufacturer", "Model", "Radio", "CPU ABI", "CPU ABI2", "Tags", "Time", "Type", "User"};
        String[] mContents = {Build.BOARD, Build.BOOTLOADER, Build.BRAND, Build.DEVICE, Build.DISPLAY, Build.FINGERPRINT, Build.HARDWARE, Build.HOST, Build.ID, Build.MANUFACTURER, Build.MODEL, Build.RADIO, Build.CPU_ABI, Build.CPU_ABI2, Build.TAGS, String.valueOf(Build.TIME), Build.TYPE, Build.USER};

        public InfoAdapter(Context context, int resource) {
            super(context, resource);
            mContext = context;
            mResource = resource;
        }

        @SuppressLint("NewApi")
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

            infoTitle.setText(mTitles[position]);
            infoText.setText(mContents[position]);

            if (Build.VERSION.SDK_INT >= 21 && position == 12) {
                infoTitle.setText("32bit ABIs");
                infoText.setText(Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
            }

            if (Build.VERSION.SDK_INT >= 21 && position == 13) {
                infoTitle.setText("64bit ABIs");
                infoText.setText(Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
            }

            return rootView;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }
    }
}
