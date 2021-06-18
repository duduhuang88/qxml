package com.qxml.gen.tableLayout;

import android.util.SparseBooleanArray;
import android.widget.TableLayout;

import java.util.regex.Pattern;

public class TableLayoutHelper {

    public static final int TYPE_STRETCHABLE_COLUMNS = 0;
    public static final int TYPE_SHRINKABLE_COLUMNS = 1;
    public static final int TYPE_COLLAPSED_COLUMNS = 2;

    private static void parseColumns(TableLayout tableLayout, String sequence, int type) {
        SparseBooleanArray columns = new SparseBooleanArray();
        Pattern pattern = Pattern.compile("\\s*,\\s*");
        String[] columnDefs = pattern.split(sequence);

        for (String columnIdentifier : columnDefs) {
            try {
                int columnIndex = Integer.parseInt(columnIdentifier);
                // only valid, i.e. positive, columns indexes are handled
                if (columnIndex >= 0) {
                    // putting true in this sparse array indicates that the
                    // column index was defined in the XML file
                    columns.put(columnIndex, true);
                    if (TYPE_STRETCHABLE_COLUMNS == type) {
                        tableLayout.setColumnStretchable(columnIndex, true);
                    } else if (TYPE_SHRINKABLE_COLUMNS == type) {
                        tableLayout.setColumnShrinkable(columnIndex, true);
                    } else {
                        tableLayout.setColumnCollapsed(columnIndex, true);
                    }
                }
            } catch (NumberFormatException e) {
                // we just ignore columns that don't exist
            }
        }
    }

    public static void setStretchedColumns(TableLayout tableLayout, String stretchColumns) {
        if (stretchColumns.charAt(0) == '*') {
            tableLayout.setStretchAllColumns(true);
        } else {
            parseColumns(tableLayout, stretchColumns, TYPE_STRETCHABLE_COLUMNS);
        }
    }

    public static void setShrinkColumns(TableLayout tableLayout, String shrinkColumns) {
        if (shrinkColumns.charAt(0) == '*') {
            tableLayout.setShrinkAllColumns(true);
        } else {
            parseColumns(tableLayout, shrinkColumns, TYPE_SHRINKABLE_COLUMNS);
        }
    }

    public static void setCollapseColumns(TableLayout tableLayout, String collapseColumns) {
        parseColumns(tableLayout, collapseColumns, TYPE_COLLAPSED_COLUMNS);
    }

}
