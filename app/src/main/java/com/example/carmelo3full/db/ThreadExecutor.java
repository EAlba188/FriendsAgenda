package com.example.carmelo3full.db;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor extends Application {

    private static final int THREADNUMBER = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService threadExecutor =
            Executors.newFixedThreadPool(THREADNUMBER);

}
