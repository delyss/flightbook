package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class Instructor extends BaseEntity{
    @DatabaseField
    private String Surname;

    @DatabaseField
    private Boolean Active;

    @DatabaseField
    private Integer InstitutionId;

//region getters and setters

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public Integer getInstitutionId() {
        return InstitutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        InstitutionId = institutionId;
    }
//endregion

    public String toString() {
        return java.text.MessageFormat.format("{0} {1}", this.getName(), this.getSurname());
    }
}
