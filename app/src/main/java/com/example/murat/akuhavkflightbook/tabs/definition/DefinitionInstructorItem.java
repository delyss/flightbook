package com.example.murat.akuhavkflightbook.tabs.definition;

import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionInstructor;

import java.util.ArrayList;
import java.util.List;

import data.entities.Instructor;

/**
 * Created by murat on 19/10/2015. flightbook
 */
public class DefinitionInstructorItem {

    private String Name;
    private Boolean Active;
    private int Id;

    public DefinitionInstructorItem(int id, String name, Boolean active) {
        super();
        Id = id;
        Name = name;
        Active = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id){
        Id = id;
    }

    public Boolean getAvtive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
