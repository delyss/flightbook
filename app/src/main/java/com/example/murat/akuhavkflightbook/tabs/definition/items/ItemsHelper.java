package com.example.murat.akuhavkflightbook.tabs.definition.items;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by muratkelekci on 13/11/2016.
 */
public class ItemsHelper {
    public static void showNotWorkingMessage(Context ctx) {
        Toast.makeText(ctx, "this feature not working now!",
                Toast.LENGTH_LONG).show();
    }
}
