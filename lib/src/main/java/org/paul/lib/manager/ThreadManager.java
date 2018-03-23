package org.paul.lib.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadManager {

    private static ThreadManager threadManager = new ThreadManager();

    private ThreadManager() {
        fixedThreadPool = Executors.newFixedThreadPool(3);
    }

    public static ThreadManager getThreadManager() {
        return threadManager;
    }

    private ExecutorService fixedThreadPool;

    public void submit(Runnable r) {
        fixedThreadPool.execute(r);
    }

    public Future<?> submitTask(Runnable r) {
        return fixedThreadPool.submit(r);
    }
}
