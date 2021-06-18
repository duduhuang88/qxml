package com.qxml.gen.view.attr;

import android.view.View;

import com.qxml.AndroidRS;
import com.qxml.gen.view.lovalVar.ViewLocalVar;
import com.yellow.qxml_annotions.Attr;

public interface TableRowAttr extends ViewLocalVar {

    @Attr(AndroidRS.attr.layout_column)
    default void onViewLayout_column(View view, int layout_column) {
        if (___cur_layout_param instanceof android.widget.TableRow.LayoutParams) {
            ((android.widget.TableRow.LayoutParams) ___cur_layout_param).column = layout_column;
        }
    }

    @Attr(AndroidRS.attr.layout_span)
    default void onViewLayout_span(View view, int layout_span) {
        if (___cur_layout_param instanceof android.widget.TableRow.LayoutParams) {
            ((android.widget.TableRow.LayoutParams) ___cur_layout_param).span = Math.max(layout_span, 1);
        }
    }

}
