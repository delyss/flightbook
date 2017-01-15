package com.example.murat.akuhavkflightbook.remote;

import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.CancelReason;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.HashMap;

import data.entities.Flight;


/**
 * Created by muratkelekci on 21/12/2016.
 */

public class PostDataJob extends Job {

    public static final int PRIORITY = 1;
    public static final String POST_TO_SERVER_GROUP_ID = "Post To Server";
    private final String senderName;
    private HashMap<String, String> postData;

    public PostDataJob(HashMap<String, String> postData, String senderName) {
        super(new Params(PRIORITY).requireNetwork().persist().groupBy(POST_TO_SERVER_GROUP_ID));
        this.postData = postData;
        this.senderName = senderName;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {

        Flight flight = new Flight();

        flight.setHarnessId(Integer.parseInt(postData.get("harnessId")));
        flight.setInstructorIdLanding(Integer.parseInt(postData.get("instructorIdLanding")));
        flight.setWingId(Integer.parseInt(postData.get("wingId")));
        flight.setTakeoffId(Integer.parseInt(postData.get("takeOffId")));
        flight.setInstructorIdTakeoff(Integer.parseInt(postData.get("instructorIdTakeoff")));
        flight.setNoteFlight(postData.get("flightEval"));
        flight.setNoteLanding(postData.get("landingEval"));
        flight.setNoteTakeoff(postData.get("landingEval"));
        flight.setScoreFlight(Integer.parseInt(postData.get("flightScore")));
        flight.setScoreLanding(Integer.parseInt(postData.get("landingScore")));
        flight.setScoreTakeoff(Integer.parseInt(postData.get("takeoffScore")));

        PostRequests p = new PostRequests();
        p.AddFlight(flight, postData);
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount,
                                                     int maxRunCount) {
        // An error occurred in onRun.
        // Return value determines whether this job should retry or cancel. You can further
        // specify a backoff strategy or change the job's priority. You can also apply the
        // delay to the whole group to preserve jobs' running order.
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }

    @Override
    protected void onCancel(@CancelReason int cancelReason, @Nullable Throwable throwable) {
        // Job has exceeded retry attempts or shouldReRunOnThrowable() has decided to cancel.
    }
}
