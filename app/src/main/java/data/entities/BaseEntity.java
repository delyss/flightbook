package data.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by muratkelekci on 22/10/2016.
 */

public class BaseEntity {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private Integer CloudId;

    @DatabaseField
    private String Name;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCloudId() {
        return CloudId;
    }

    public void setCloudId(Integer cloudId) {
        CloudId = cloudId;
    }
}
