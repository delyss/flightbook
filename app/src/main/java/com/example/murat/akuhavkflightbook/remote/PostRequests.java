package com.example.murat.akuhavkflightbook.remote;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.murat.akuhavkflightbook.App;
import com.google.inject.Inject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import data.DbHelper;
import data.entities.Flight;
import data.repositories.Flight.FlightDataProvider;
import data.repositories.Flight.FlightRepository;
import data.repositories.Pilot.PilotRepository;

/**
 * Created by muratkelekci on 14/01/2017.
 */

public class PostRequests {

    @Inject
    private FlightRepository flightRepository;
    @Inject
    private PilotRepository pilotRepository;

    public void AddFlight(final Flight flight, HashMap<String, String> postData)
    {
        DbHelper db = new DbHelper(App.getContext());
        FlightDataProvider pv = new FlightDataProvider(db);
        final FlightRepository repo = new FlightRepository(App.getContext(), pv);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        repo.Save(flight);
//                        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
//                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        ApiAddFlight call = new ApiAddFlight(
                postData.get("pilotName"), postData.get("wingStr"), postData.get("flightEval"),
                postData.get("flightScore"), postData.get("harnessStr"), postData.get("insLandingStr"),
                postData.get("insTakeoffStr"), postData.get("landingEval"), postData.get("landingScore"),
                postData.get("takeoffEval"), postData.get("takeoffScore"), postData.get("takeoffStr")
                , responseListener);

        RequestQueue queue = Volley.newRequestQueue(App.getContext());
        queue.add(call);
    }
}
