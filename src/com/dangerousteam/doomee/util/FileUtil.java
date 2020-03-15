package com.dangerousteam.doomee.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * Create a file in a specific format.
     * Like file(1).png
     */
    public static File createNewFile(File from) {
        String[] fileInfo = getFileInfo(from);
        String toPrefix = fileInfo[0];
        String toSuffix = fileInfo[1];

        File directory = from.getParentFile();
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Created directory " + directory.getAbsolutePath());
            }
        }
        File newFile = new File(directory, toPrefix + toSuffix);
        for (int i = 1; newFile.exists() && i < Integer.MAX_VALUE; i++) {
            newFile = new File(directory, toPrefix + '(' + i + ')' + toSuffix);
        }
        return newFile;
    }

    /**
     * Get file information. {prefix, suffix}
     */
    public static String[] getFileInfo(File from) {
        String fileName = from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix = "";
        String toSuffix = "";
        if (index == -1) {
            toPrefix = fileName;
        } else {
            toPrefix = fileName.substring(0, index);
            toSuffix = fileName.substring(index, fileName.length());
        }
        return new String[]{toPrefix, toSuffix};
    }

    /**
     * TXT document get list.
     */
    public static List<String> txt2StringList(File file) {
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                stringList.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

}
