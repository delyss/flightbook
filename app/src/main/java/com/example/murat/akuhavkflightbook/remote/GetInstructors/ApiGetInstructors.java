package com.example.murat.akuhavkflightbook.remote.GetInstructors;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muratkelekci on 02/03/2017.
 */

public class ApiGetInstructors extends StringRequest {
    private static final String API_URL = "http://tty.tc/e2a53f56-32ba-49ee-947e-53f9f16bf2fe/api/getinstructors.php";
    private Map<String, String> params;

    ApiGetInstructors( String takeoffScore, String takeoffStr, Response.Listener<String> listener) {
        super(Method.POST, API_URL, listener, null);

        params = new HashMap<>();
        params.put("takeoffScore",takeoffScore);
        params.put("takeoffStr",takeoffStr);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
