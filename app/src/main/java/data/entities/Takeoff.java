package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 12/08/2015. flightbook
 */
@DatabaseTable
public class Takeoff {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private String Name;

    @DatabaseField
    private int Altitude;

    @DatabaseField
    private String Description;

    @DatabaseField
    private String Characteristic;

    @DatabaseField
    private String LocationCountry;

    @DatabaseField
    private String LocationCity;

    @DatabaseField
    private String LocationRegion;

    @DatabaseField
    private String LocationSubRegion;
    // not editable
    @DatabaseField
    private Boolean IsConstant;

//region getters and setters

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAltitude() {
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

    public String getCharacteristic() {
        return Characteristic;
    }

    public void setCharacteristic(String characteristic) {
        Characteristic = characteristic;
    }

    public String getLocationCountry() {
        return LocationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        LocationCountry = locationCountry;
    }

    public String getLocationCity() {
        return LocationCity;
    }

    public void setLocationCity(String locationCity) {
        LocationCity = locationCity;
    }

    public String getLocationRegion() {
        return LocationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        LocationRegion = locationRegion;
    }

    public String getLocationSubRegion() {
        return LocationSubRegion;
    }

    public void setLocationSubRegion(String locationSubRegion) {
        LocationSubRegion = locationSubRegion;
    }

    public Boolean getIsConstant() {
        return IsConstant;
    }

    public void setIsConstant(Boolean isConstant) {
        IsConstant = isConstant;
    }
//endregion

}
