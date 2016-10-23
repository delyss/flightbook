package com.example.murat.akuhavkflightbook.tabs.flight;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import java.util.Calendar;
import java.util.List;

import data.entities.BaseEntity;
import data.repositories.Harness.HarnessRepository;
import data.repositories.Instructor.InstructorRepository;
import data.repositories.Takeoff.TakeoffRepository;
import data.repositories.Wing.WingRepository;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;


public class EditFlight extends RoboFragmentActivity {

    @Inject
    HarnessRepository harnessRepository;
    @Inject
    WingRepository wingRepository;
    @Inject
    InstructorRepository instructorRepository;
    @Inject
    TakeoffRepository takeoffRepository;

    @InjectView(R.id.spnHarness)
    Spinner spnHarness;
    @InjectView(R.id.spnWing)
    Spinner spnWing;
    @InjectView(R.id.spnTakeOff)
    Spinner spnTakeOff;
    @InjectView(R.id.spnInstructorLanding)
    Spinner spnInstructorLanding;
    @InjectView(R.id.spnInstructorTakeOff)
    Spinner spnInstructorTakeOff;

    static Button btnEditFlightDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight);
        btnEditFlightDate = (Button) findViewById(R.id.btnEditFlightDate);

        fillSpinner(spnHarness, harnessRepository.QueryForAll());
        fillSpinner(spnWing, wingRepository.QueryForAll());
        fillSpinner(spnInstructorLanding, instructorRepository.QueryForAll());
        fillSpinner(spnInstructorTakeOff, instructorRepository.QueryForAll());
        fillSpinner(spnTakeOff, takeoffRepository.QueryForAll());

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int harnessId = ((BaseEntity) spnHarness.getSelectedItem()).getId();
                        int wingId = ((BaseEntity) spnHarness.getSelectedItem()).getId();
                        Toast.makeText(getApplicationContext(), harnessId + "-" + wingId, Toast.LENGTH_LONG).show();
                    }
                });
    }


    private SpinAdapter fillSpinner(Spinner spn, List<?> list) {
        SpinAdapter adapter = new SpinAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        return adapter;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            btnEditFlightDate.setText(new StringBuilder()
                    .append(month + 1).append("-").append(day).append("-")
                    .append(year).append(" "));
        }
    }
}

class SpinAdapter<T> extends ArrayAdapter<T> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<T> values;

    public SpinAdapter(Context context, int textViewResourceId,
                       List<T> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;

    }

    public int getCount() {
        return values.size();
    }

    public T getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(((BaseEntity) values.get(position)).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setText(((BaseEntity) values.get(position)).getName());

        return label;
    }
}
