package com.example.murat.akuhavkflightbook.remote;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muratkelekci on 07/11/2016.
 */

public class ApiAddFlight extends StringRequest {
    private static final String API_URL = "http://tty.tc/e2a53f56-32ba-49ee-947e-53f9f16bf2fe/api/addflight.php";
    private Map<String, String> params;

    public ApiAddFlight(String pilotName, String wingStr,  String flightEval, String flightScore, String harnessStr,
                 String insLandingStr, String insTakeoffStr, String landingEval, String landingScore, String takeoffEval,
                 String takeoffScore, String takeoffStr, Response.Listener<String> listener) {
        super(Method.POST, API_URL, listener, null);

        params = new HashMap<>();
        params.put("pilotName",pilotName);
        params.put("wingStr",wingStr);
        params.put("flightEval",flightEval);
        params.put("flightScore",flightScore);
        params.put("harnessStr",harnessStr);
        params.put("insLandingStr",insLandingStr);
        params.put("insTakeoffStr",insTakeoffStr);
        params.put("landingEval",landingEval);
        params.put("landingScore",landingScore);
        params.put("takeoffEval",takeoffEval);
        params.put("takeoffScore",takeoffScore);
        params.put("takeoffStr",takeoffStr);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
