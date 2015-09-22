package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class Pilot {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private String Name;

    @DatabaseField
    private String Surname;

    @DatabaseField
    private String BloodGroup;

    @DatabaseField
    private String Email;

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

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getBloodGroup() { return BloodGroup; }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }
//endregion
}
