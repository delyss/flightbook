package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.roboguice.shaded.goole.common.base.Joiner;

import java.util.List;

/**
 * Created by murat on 12/08/2015. flightbook
 */
@DatabaseTable
public class Takeoff  extends BaseEntity{
    @DatabaseField
    private int Altitude;

    @DatabaseField
    private String Description;

    @DatabaseField
    private String Characteristic;

    @DatabaseField
    private String Location;
    // not editable
    @DatabaseField
    private Boolean IsConstant;

//region getters and setters

    public Integer getAltitude() {
        return Altitude;
    }

    public void setAltitude(int altitude) {
        Altitude = altitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String[] getCharacteristic() {
        return Characteristic.split(",");
    }

    public void setCharacteristic(List<String> characteristic) {
        Characteristic = Joiner.on(",").join(characteristic);
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Boolean getIsConstant() {
        return IsConstant;
    }

    public void setIsConstant(Boolean isConstant) {
        IsConstant = isConstant;
    }
//endregion

}
