package com.hx.settingsx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static String readOneLine(String sysFile) {
        Runtime runtime = Runtime.getRuntime();
        String cmd = "su -c cat " + sysFile;
        try {
            Process process = runtime.exec(cmd);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            return stdInput.readLine();
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        return null;
    }

    public static void writeValue(String filename, String value) {
        Runtime runtime = Runtime.getRuntime();
        String cmd = "su -c echo " + value + " > " + filename;
        try {
            runtime.exec(cmd);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}
