package com.qxml.gen.listView;

import android.content.Context;
import android.widget.ListView;

public class ListViewHelper {

    public static void setDefaultAdapter(ListView listView, Context context, int entriesId) {
        final CharSequence[] entries = context.getResources().getTextArray(entriesId);
        listView.setAdapter(new android.widget.ArrayAdapter<>(context, android.R.layout.simple_list_item_1, entries));
    }

}
