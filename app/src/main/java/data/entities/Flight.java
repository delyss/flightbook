package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by murat on 19/11/2015. flightbook
 */
@DatabaseTable
public class Flight {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private Integer WingId;

    @DatabaseField
    private Integer HarnessId;

    @DatabaseField
    private Integer TakeoffId;

    @DatabaseField
    private Date FlightDate;

    @DatabaseField
    private Date FlightTime;

    @DatabaseField
    private Integer InstructorIdTakeoff;

    @DatabaseField
    private Integer InstructorIdLanding;

    @DatabaseField
    private Integer ScoreTakeoff;

    @DatabaseField
    private Integer ScoreFlight;

    @DatabaseField
    private Integer ScoreLanding;

    @DatabaseField
    private String NoteTakeoff;

    @DatabaseField
    private String NoteFlight;

    @DatabaseField
    private String NoteLanding;

    //region getters and setters

    public Integer getId() {
        return Id;
    }

    public Integer getWingId() {
        return WingId;
    }

    public void setWingId(Integer wingId) {
        WingId = wingId;
    }

    public Integer getHarnessId() {
        return HarnessId;
    }

    public void setHarnessId(Integer harnessId) {
        HarnessId = harnessId;
    }

    public Integer getTakeoffId() {
        return TakeoffId;
    }

    public void setTakeoffId(Integer takeoffId) {
        TakeoffId = takeoffId;
    }

    public Date getFlightDate() {
        return FlightDate;
    }

    public void setFlightDate(Date flightDate) {
        FlightDate = flightDate;
    }

    public Date getFlightTime() {
        return FlightTime;
    }

    public void setFlightTime(Date flightTime) {
        FlightTime = flightTime;
    }

    public Integer getInstructorIdTakeoff() {
        return InstructorIdTakeoff;
    }

    public void setInstructorIdTakeoff(Integer instructorIdTakeoff) {
        InstructorIdTakeoff = instructorIdTakeoff;
    }

    public Integer getInstructorIdLanding() {
        return InstructorIdLanding;
    }

    public void setInstructorIdLanding(Integer instructorIdLanding) {
        InstructorIdLanding = instructorIdLanding;
    }

    public Integer getScoreTakeoff() {
        return ScoreTakeoff;
    }

    public void setScoreTakeoff(Integer scoreTakeoff) {
        ScoreTakeoff = scoreTakeoff;
    }

    public Integer getScoreFlight() {
        return ScoreFlight;
    }

    public void setScoreFlight(Integer scoreFlight) {
        ScoreFlight = scoreFlight;
    }

    public Integer getScoreLanding() {
        return ScoreLanding;
    }

    public void setScoreLanding(Integer scoreLanding) {
        ScoreLanding = scoreLanding;
    }

    public String getNoteTakeoff() {
        return NoteTakeoff;
    }

    public void setNoteTakeoff(String noteTakeoff) {
        NoteTakeoff = noteTakeoff;
    }

    public String getNoteFlight() {
        return NoteFlight;
    }

    public void setNoteFlight(String noteFlight) {
        NoteFlight = noteFlight;
    }

    public String getNoteLanding() {
        return NoteLanding;
    }

    public void setNoteLanding(String noteLanding) {
        NoteLanding = noteLanding;
    }
    //endregion
}
