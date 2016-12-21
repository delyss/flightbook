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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.murat.akuhavkflightbook.ApiAddFlight;
import com.example.murat.akuhavkflightbook.BaseEntitySpinAdapter;
import com.example.murat.akuhavkflightbook.R;
import com.google.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.entities.BaseEntity;
import data.entities.Flight;
import data.entities.Pilot;
import data.repositories.Flight.FlightRepository;
import data.repositories.Harness.HarnessRepository;
import data.repositories.Instructor.InstructorRepository;
import data.repositories.Pilot.PilotRepository;
import data.repositories.Takeoff.TakeoffRepository;
import data.repositories.Wing.WingRepository;
import org.json.JSONException;
import org.json.JSONObject;
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

        final Flight f = new Flight();
        f.setFlightDate(flightDate);
        f.setHarnessId(harness.getId());
        f.setInstructorIdLanding(insLand.getId());
        f.setWingId(wing.getId());
        f.setTakeoffId(takeOff.getId());
        f.setInstructorIdTakeoff(insTakeoff.getId());
        f.setNoteFlight(txtEvalFlight.getText().toString());
        f.setNoteLanding(txtEvalLanding.getText().toString());
        f.setNoteTakeoff(txtEvalTakeoff.getText().toString());
        f.setScoreFlight(seekbarScorFlight.getProgress());
        f.setScoreLanding(seekbarScorLanding.getProgress());
        f.setScoreTakeoff(seekbarScorTakeoff.getProgress());


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        flightRepository.Save(f);
                        Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Pilot pilot = pilotRepository.getPilot();
        ApiAddFlight call = new ApiAddFlight(pilot.getName() + " " + pilot.getLastName(),
                wing.getName(), txtEvalFlight.getText().toString(),
                String.valueOf(seekbarScorFlight.getProgress()),
                harness.getName(), insLand.getName(), insTakeoff.getName(), txtEvalLanding.getText().toString(),
                String.valueOf(seekbarScorLanding.getProgress()), txtEvalTakeoff.getText().toString(),
                String.valueOf(seekbarScorTakeoff.getProgress()), takeOff.getName(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(EditFlight.this);
        queue.add(call);

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

