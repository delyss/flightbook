package com.example.murat.akuhavkflightbook.tabs.flight;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.murat.akuhavkflightbook.App;
import com.example.murat.akuhavkflightbook.BaseEntitySpinAdapter;
import com.example.murat.akuhavkflightbook.remote.AddFlight.PostDataJob;
import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import data.entities.BaseEntity;
import data.entities.Pilot;
import data.repositories.Flight.FlightRepository;
import data.repositories.Harness.HarnessRepository;
import data.repositories.Instructor.InstructorRepository;
import data.repositories.Pilot.PilotRepository;
import data.repositories.Takeoff.TakeoffRepository;
import data.repositories.Wing.WingRepository;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;


public class EditFlight extends RoboFragmentActivity {

    @Inject
    private HarnessRepository harnessRepository;
    @Inject
    private WingRepository wingRepository;
    @Inject
    private InstructorRepository instructorRepository;
    @Inject
    private TakeoffRepository takeoffRepository;
    @Inject
    private FlightRepository flightRepository;
    @Inject
    private PilotRepository pilotRepository;

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

    @InjectView(R.id.seekbarScoreTakeoff)
    SeekBar seekbarScorTakeoff;
    @InjectView(R.id.seekbarScoreFlight)
    SeekBar seekbarScorFlight;
    @InjectView(R.id.seekbarScoreLanding)
    SeekBar seekbarScorLanding;

    @InjectView(R.id.lblScoreTakeoff)
    TextView lblScoreTakeoff;
    @InjectView(R.id.lblScoreFlight)
    TextView lblScoreFlight;
    @InjectView(R.id.lblScoreLanding)
    TextView lblScoreLanding;

    @InjectView(R.id.txtEvalTakeoff)
    EditText txtEvalTakeoff;
    @InjectView(R.id.txtEvalFlight)
    EditText txtEvalFlight;
    @InjectView(R.id.txtEvalLanding)
    EditText txtEvalLanding;

    static Button btnEditFlightDate;
    static Date flightDate;

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
        addSeekBarEvents(seekbarScorFlight, lblScoreFlight);
        addSeekBarEvents(seekbarScorLanding, lblScoreLanding);
        addSeekBarEvents(seekbarScorTakeoff, lblScoreTakeoff);
    }

    public void saveFlight(View v) {

        BaseEntity harness = ((BaseEntity) spnHarness.getSelectedItem());
        BaseEntity wing = ((BaseEntity) spnWing.getSelectedItem());
        BaseEntity takeOff = ((BaseEntity) spnTakeOff.getSelectedItem());
        BaseEntity insLand = ((BaseEntity) spnInstructorLanding.getSelectedItem());
        BaseEntity insTakeoff = ((BaseEntity) spnInstructorTakeOff.getSelectedItem());

        Pilot pilot = pilotRepository.getPilot();
        HashMap<String, String> flightParams = new HashMap<>(18);
        flightParams.put("pilotName", pilot.getName() + " " + pilot.getLastName());
        flightParams.put("wingStr", wing.getName());
        flightParams.put("flightEval", txtEvalFlight.getText().toString());
        flightParams.put("flightScore", String.valueOf(seekbarScorFlight.getProgress()));
        flightParams.put("harnessStr", harness.getName());
        flightParams.put("insLandingStr", insLand.getName());
        flightParams.put("insTakeoffStr", insTakeoff.getName());
        flightParams.put("landingEval", txtEvalLanding.getText().toString());
        flightParams.put("landingScore", String.valueOf(seekbarScorLanding.getProgress()));
        flightParams.put("takeoffEval", txtEvalTakeoff.getText().toString());
        flightParams.put("takeoffScore", String.valueOf(seekbarScorTakeoff.getProgress()));
        flightParams.put("takeoffStr", takeOff.getName());
        flightParams.put("flightDate", flightDate.toString());
        flightParams.put("harnessId", harness.getId().toString());
        flightParams.put("instructorIdLanding", insLand.getId().toString());
        flightParams.put("wingId", wing.getId().toString());
        flightParams.put("takeOffId", takeOff.getId().toString());
        flightParams.put("instructorIdTakeoff", insTakeoff.getId().toString());

        ((App) getApplication().getApplicationContext())
                .getJobManager()
                .addJobInBackground(new PostDataJob(flightParams));
    }

    private void addSeekBarEvents(SeekBar sb, final TextView lbl) {

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lbl.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    private BaseEntitySpinAdapter fillSpinner(Spinner spn, List<?> list) {
        BaseEntitySpinAdapter adapter = new BaseEntitySpinAdapter<>(this, android.R.layout.simple_spinner_item, list);
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

            flightDate = c.getTime();
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            btnEditFlightDate.setText(new StringBuilder()
                    .append(month + 1).append("-").append(day).append("-")
                    .append(year).append(" "));
        }
    }
}

