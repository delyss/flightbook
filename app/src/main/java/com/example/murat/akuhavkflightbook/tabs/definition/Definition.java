package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionHarness;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionProfile;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionTakeoff;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionWing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murat on 13/08/2015. flightbook
 */
public class Definition {

    private String Name;
    private String Icon;

    public Definition(String name, String icon){
        super();
        this.Name = name;
        this.Icon = icon;
    }

    public Definition(View view, final Activity activity)
    {
        getDefinitionList();
        final ListView lwDefinitions = (ListView)view.findViewById(R.id.lwDefinitions);
        DefinitionAdapter adapter = new DefinitionAdapter(activity, definitionLists);
        lwDefinitions.setAdapter(adapter);

        lwDefinitions.setClickable(true);
        lwDefinitions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Definition o = (Definition)arg0.getItemAtPosition(position);
                Intent intent;
                switch (o.Name){
                    case "Pilot":
                        intent = new Intent(activity, DefinitionProfile.class);
                        break;
                    case "Wings":
                        intent = new Intent(activity, DefinitionWing.class);
                        break;
                    case "Takeoff":
                        intent = new Intent(activity, DefinitionTakeoff.class);
                        break;
                    case "Harness":
                        intent = new Intent(activity, DefinitionHarness.class);
                        break;
                    default:
                        intent = new Intent(activity, DefinitionProfile.class);
                        break;
                }
                activity.startActivity(intent);
            }
        });
    }

    private static List<Definition> definitionLists;
    public static List<Definition> getDefinitionList() {
        definitionLists = new ArrayList<>();
        definitionLists.add(new Definition("Pilot", "ico1"));
        definitionLists.add(new Definition("Wings", "ico1"));
        definitionLists.add(new Definition("Takeoff", "ico1"));
        definitionLists.add(new Definition("Harness", "ico1"));
        return definitionLists;
    }

    //region getters and setters
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

