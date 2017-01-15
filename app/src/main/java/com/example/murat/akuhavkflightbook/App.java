package com.example.murat.akuhavkflightbook;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.example.murat.akuhavkflightbook.service.MyGcmJobService;
import com.example.murat.akuhavkflightbook.service.MyJobService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by muratkelekci on 12/01/2017.
 */

public class App extends Application {

    private static Context mContext;
    private JobManager jobManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        getJobManager();
    }

    public synchronized JobManager getJobManager() {
        if (jobManager == null) {
            com.birbit.android.jobqueue.config.Configuration.Builder builder = new com.birbit.android.jobqueue.config.Configuration.Builder(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(this, MyJobService.class), true);
            } else {
                int enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
                if (enableGcm == ConnectionResult.SUCCESS) {
                    builder.scheduler(GcmJobSchedulerService.createSchedulerFor(this,
                            MyGcmJobService.class), true);
                }
            }
            jobManager = new JobManager(builder.build());
        }
        return jobManager;
    }

    public static Context getContext() {
        return mContext;
    }
}