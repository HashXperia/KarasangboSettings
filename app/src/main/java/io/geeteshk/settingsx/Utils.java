package io.geeteshk.settingsx;

import java.io.File;

public class Utils {

    public static boolean isDeviceRooted() {
        String[] places = {
                "/sbin/", "/system/bin/", "/system/xbin/",
                "/data/local/xbin/", "/data/local/bin/",
                "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
        };

        for (String where : places) {
            if (new File(where + "su").exists()) {
                return true;
            }
        }

        return false;
    }

    public static void getRootAccess() {
        try {
            Process process = Runtime.getRuntime().exec("su");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
