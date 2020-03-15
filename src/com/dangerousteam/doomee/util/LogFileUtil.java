/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee.util;

import java.io.FileWriter;
import java.io.IOException;

public class LogFileUtil {
    /**
     * Saved log file.
     */
    public static void saveAsFileWriter(String fileName, String content) {
        try {
            // Open a file writer, the second parameter true in the constructor indicates that the file is written in append form.
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
