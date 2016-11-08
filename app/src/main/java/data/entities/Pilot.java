package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by murat on 09/08/2015. flightbook
 */
@DatabaseTable
public class Pilot extends BaseEntity {
    @DatabaseField
    private String LastName;

    @DatabaseField
    private String BloodGroup;

    @DatabaseField
    private String Email;

    @DatabaseField
    private String Password;

    @DatabaseField
    private Boolean Registered;

    @DatabaseField
    private String Phone;
//region getters and setters

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean getRegistered() {
        if (Registered == null)
            return false;
        return Registered;
    }

    public void setRegistered(boolean registered) {
        Registered = registered;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
//endregion
}
