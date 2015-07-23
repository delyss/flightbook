

package com.example.murat.akuhavkflightbook.tabs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.R;

import roboguice.RoboGuice;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;


@ContentView(R.layout.tab_pilot_layout)
public class Pilot  { //extends RoboGuice

    @InjectView(R.id.txtName)
    TextView name;
    @InjectResource(R.string.app_name)
    String myName;

    public Pilot(View view) {

    }

    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        name.setText("Hello, " + myName);
    }
}

//    EditText txtName;
//    public Pilot(View view)
//    {
//        txtName = (EditText)view.findViewById(R.id.txtName);
//        EditText txtEmail = (EditText) view.findViewById(R.id.txtEmail);
//        Spinner cmbBloodGroup = (Spinner) view.findViewById(R.id.cmbBloodGroup);
//
//        final Button btnSave = (Button) view.findViewById(R.id.btnSave);
//
//        txtName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                btnSave.setEnabled(!txtName.getText().toString().trim().isEmpty());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
