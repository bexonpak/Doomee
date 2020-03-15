/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee.task;

import com.dangerousteam.doomee.DownloadFile;

import java.util.concurrent.Callable;

public class DownloadTask implements Callable<Integer> {
    private Integer taskID;
    private String url;
    private String output;
    private Long createdTime;

    public DownloadTask(String url, String output, Integer taskID, Long createdTime) {
        this.url = url;
        this.output = output;
        this.taskID = taskID;
        this.createdTime = createdTime;
    }

    public Integer call() {
        System.out.println("任务[" + taskID + "]开始执行: " + url);
        DownloadFile.downLoadFromUrl(url, output, createdTime);
        System.out.println("\uD83C\uDF7A 任务[" + taskID + "]下载完成 Url: " + url + "  保存位置: " + output);
        return taskID;
    }
}
