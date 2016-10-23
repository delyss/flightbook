package com.example.murat.akuhavkflightbook.tabs.definition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.murat.akuhavkflightbook.R;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionHarness;
import com.example.murat.akuhavkflightbook.tabs.definition.items.DefinitionInstructor;
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
    private Integer Icon;
    private Integer BgColor;

    public Definition(String name, Integer icon, Integer bgColor){
        super();
        this.Name = name;
        this.Icon = icon;
        this.BgColor = bgColor;
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
                    case "Instructor":
                        intent = new Intent(activity, DefinitionInstructor.class);
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
        definitionLists.add(new Definition("Pilot", R.mipmap.ic_launcher_definition_pilot,
                Color.rgb(61,187,245)));
        definitionLists.add(new Definition("Wings", R.mipmap.ic_launcher_definition_wing,
                Color.rgb(2,136,209)));
        definitionLists.add(new Definition("Takeoff", R.mipmap.ic_launcher_definition_takeoff,
                Color.rgb(68,138,255)));
        definitionLists.add(new Definition("Harness", R.mipmap.ic_launcher_definition_harness,
                Color.rgb(68,121,211)));
        definitionLists.add(new Definition("Instructor", R.mipmap.ic_launcher_definition_harness,
                Color.rgb(68,121,211)));
        return definitionLists;
    }

    //region getters and setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getIcon() {
        return Icon;
    }

    public void setIcon(Integer icon) {
        Icon = icon;
    }

    public Integer getBgColor() {
        return BgColor;
    }

    public void setBgColor(int bgColor) {
        BgColor = bgColor;
    }
    //endregion
}

