/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee;

import com.dangerousteam.doomee.bean.DownProp;
import com.dangerousteam.doomee.task.DownloadTask;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadRunner {

    /**
     * Create a downloaded thread pool.
     */
    public static boolean dlRunner(DownProp prop, Integer task) {
        final long createdTime = System.currentTimeMillis();
        boolean isComplete = true;
        // Created a thread pool.
        ExecutorService pools = Executors.newFixedThreadPool(task);
        CompletionService<Integer> service = new ExecutorCompletionService<>(pools);
        // Created multiple tasks with return values.
        for (int i = 0; i < prop.getUrl().length; i++) {
            service.submit(new DownloadTask(prop.getUrl()[i], prop.getOutput(), i, createdTime));
        }
        for (int i = 0; i < prop.getUrl().length; i++) {
            try {
                service.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                isComplete = false;
            }
        }
        pools.shutdown();
        return isComplete;
    }
}