/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee.task;

import com.dangerousteam.doomee.DownloadFile;

import java.util.concurrent.Callable;

public class DownloadTask implements Callable<Integer> {
    private final Integer taskID;
    private final String url;
    private final String output;
    private final Long createdTime;

    public DownloadTask(String url, String output, Integer taskID, Long createdTime) {
        this.url = url;
        this.output = output;
        this.taskID = taskID;
        this.createdTime = createdTime;
    }

    public Integer call() {
        System.out.println("Task[" + taskID + "] Start: " + url);
        DownloadFile.downLoadFromUrl(url, output, createdTime);
        System.out.println("\uD83C\uDF7A Task[" + taskID + "] Completed Url: " + url + "  Local: " + output);
        return taskID;
    }
}
