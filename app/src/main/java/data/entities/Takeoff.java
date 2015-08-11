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
//endregion

}
