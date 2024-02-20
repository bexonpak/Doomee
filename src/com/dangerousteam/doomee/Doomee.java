/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee;

import com.dangerousteam.doomee.bean.DownProp;

public class Doomee {
    private static DownProp downProp;

    /**
     * The default number of tasks is 3.
     */
    protected static int task = 3;

    /**
     * Set the number of tasks. If the {@code task} value is less than the Url array length,
     * the Url array length will be used as the {@code task} value
     */
    public Doomee tasks(int taskNum) {
        if (downProp.getUrl().length < taskNum) {
            task = downProp.getUrl().length;
        } else {
            task = taskNum;
        }
        return this;
    }

    /**
     * Download properties.
     */
    public static Doomee load(String output, String[] url) {
        Doomee doomee = new Doomee();
        downProp = new DownProp.Builder()
                .output(output)
                .url(url)
                .build();
        return doomee;
    }

    /**
     * Start download.
     */
    public void start() {
        boolean isComplete = DownloadRunner.dlRunner(downProp, task);
        if (isComplete) {
            System.out.format("%n\33[32;4m✅ Done!%n");
        } else {
            System.err.println("There is an exception in the download, please check the error.log file.");
        }
    }

}
