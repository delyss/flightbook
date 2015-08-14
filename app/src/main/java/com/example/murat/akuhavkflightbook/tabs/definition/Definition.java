package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.example.murat.akuhavkflightbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murat on 13/08/2015. flightbook
 */
public class Definition {

    private String Name;
    private String Icon;
    private Activity activity;

    public Definition(String name, String icon){
        super();
        this.Name = name;
        this.Icon = icon;
    }

    public Definition(View view, Activity activity)
    {
        getDefinitionList();
        this.activity = activity;
        final ListView lwDefinitions = (ListView)view. findViewById(R.id.lwDefinitions);
        DefinitionAdapter adaptorumuz=new DefinitionAdapter(activity, definitionLists);
        lwDefinitions.setAdapter(adaptorumuz);
    }

    private static List<Definition> definitionLists;
    public static List<Definition> getDefinitionList() {
        definitionLists = new ArrayList<>();
        definitionLists.add(new Definition("Instructor", "ico1"));
        definitionLists.add(new Definition("Takeoff", "ico1"));
        definitionLists.add(new Definition("Pilot", "ico1"));
        definitionLists.add(new Definition("Harness", "ico1"));
        definitionLists.add(new Definition("Instructor", "ico1"));
        definitionLists.add(new Definition("Takeoff", "ico1"));
        definitionLists.add(new Definition("Pilot", "ico1"));
        definitionLists.add(new Definition("Harness", "ico1"));
        definitionLists.add(new Definition("Instructor", "ico1"));
        definitionLists.add(new Definition("Takeoff", "ico1"));
        definitionLists.add(new Definition("Pilot", "ico1"));
        definitionLists.add(new Definition("Harness", "ico1"));
        return definitionLists;
    }

    //region getters ansd setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }
    //endregion
}

