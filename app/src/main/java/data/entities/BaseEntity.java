package data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.UuidType;

import java.util.UUID;

/**
 * Created by muratkelekci on 22/10/2016.
 */

public class BaseEntity {
    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private UuidType CloudId;

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

    public UuidType getCloudId() {
        return CloudId;
    }

    public void setCloudId(UuidType cloudId) {
        CloudId = cloudId;
    }
}
