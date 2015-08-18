package com.example.murat.akuhavkflightbook.tabs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.murat.akuhavkflightbook.R;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


 public class Pilot extends Activity {

//    @InjectView(R.id.txtName)
    TextView txtName;

//    @InjectView(R.id.btnSave)
    Button btnSave;


     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

     }


     public Pilot(View view) {
         txtName = (TextView)view.findViewById(R.id.txtName);
         btnSave = (Button)view.findViewById(R.id.btnSave);

         txtName.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 btnSave.setEnabled(!txtName.getText().toString().trim().isEmpty());
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
     }




//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//

//    }
}

/*
    @InjectView(R.id.txtName)
    TextView txtName;

    @InjectView(R.id.btnSave)
    Button btnSave;


*
*            txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!txtName.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
* */