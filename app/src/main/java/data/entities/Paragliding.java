package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class Paragliding {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private String Name;

    @DatabaseField
    private Boolean IsFly;

    @DatabaseField
    private String Classification;

    @DatabaseField
    private Integer WeightMin;

    @DatabaseField
    private Integer WeightMax;


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

    public Boolean getIsFly() {
        return IsFly;
    }

    public void setIsFly(Boolean isFly) {
        IsFly = isFly;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public Integer getWeightMin() {
        return WeightMin;
    }

    public void setWeightMin(Integer weightMin) {
        WeightMin = weightMin;
    }

    public Integer getWeightMax() {
        return WeightMax;
    }

    public void setWeightMax(Integer weightMax) {
        WeightMax = weightMax;
    }

//endregion
}
