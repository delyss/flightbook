package com.example.murat.akuhavkflightbook;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muratkelekci on 07/11/2016.
 */

class ApiAddUser extends StringRequest {
    private static final String API_URL = "http://tty.tc/e2a53f56-32ba-49ee-947e-53f9f16bf2fe/api/adduser.php";
    private Map<String, String> params;

    ApiAddUser(String firstName, String lastName, String email, String phone, String password, Response.Listener<String> listener) {
        super(Method.POST, API_URL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);
        params.put("phone", phone);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
