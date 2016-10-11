package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class Harness {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private String Name;

    @DatabaseField
    private String Size;

    // this field for instution harness.
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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public Boolean getIsConstant() {
        return IsConstant;
    }

    public void setIsConstant(Boolean constant) {
        IsConstant = constant;
    }

//endregion
}
