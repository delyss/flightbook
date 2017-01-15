package com.example.murat.akuhavkflightbook.service;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.example.murat.akuhavkflightbook.App;

/**
 * Created by hasanguner on 24/08/16.
 */
public class MyJobService extends FrameworkJobSchedulerService {

    @NonNull
    @Override
    protected JobManager getJobManager() {
        return ((App)getApplicationContext()).getJobManager();
    }

}