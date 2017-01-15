package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class LastDates extends BaseEntity {

    @DatabaseField
    private Date LastFlightDate;

    @DatabaseField
    private Date LastHarnessDate;

    @DatabaseField
    private Date LastInstitutionDate;

    @DatabaseField
    private Date LastInstructorDate;

    @DatabaseField
    private Date LastPilotDate;

    @DatabaseField
    private Date LastTakeoffDate;

    @DatabaseField
    private Date LastWingDate;

    public Date getLastFlightDate() {
        return LastFlightDate;
    }

    public void setLastFlightDate(Date lastFlightDate) {
        LastFlightDate = lastFlightDate;
    }

    public Date getLastHarnessDate() {
        return LastHarnessDate;
    }

    public void setLastHarnessDate(Date lastHarnessDate) {
        LastHarnessDate = lastHarnessDate;
    }

    public Date getLastInstitutionDate() {
        return LastInstitutionDate;
    }

    public void setLastInstitutionDate(Date lastInstitutionDate) {
        LastInstitutionDate = lastInstitutionDate;
    }

    public Date getLastInstructorDate() {
        return LastInstructorDate;
    }

    public void setLastInstructorDate(Date lastInstructorDate) {
        LastInstructorDate = lastInstructorDate;
    }

    public Date getLastPilotDate() {
        return LastPilotDate;
    }

    public void setLastPilotDate(Date lastPilotDate) {
        LastPilotDate = lastPilotDate;
    }

    public Date getLastTakeoffDate() {
        return LastTakeoffDate;
    }

    public void setLastTakeoffDate(Date lastTakeoffDate) {
        LastTakeoffDate = lastTakeoffDate;
    }

    public Date getLastWingDate() {
        return LastWingDate;
    }

    public void setLastWingDate(Date lastWingDate) {
        LastWingDate = lastWingDate;
    }
}
