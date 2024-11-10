package com.example.tcc.core.extensions

import android.app.Activity
import android.view.View
import android.widget.PopupMenu

fun Activity.showPopupMenu(
    anchorView: View,
    items: List<Pair<Long, String>>,
    onItemClick: (Pair<Long, String>) -> Unit
): PopupMenu = PopupMenu(this, anchorView).apply {
    items.forEachIndexed { index, pair ->
        menu.add(0, pair.first.toInt(), index, pair.second)
    }
    setOnMenuItemClickListener {
        onItemClick(items[it.order])
        true
    }
    show()
}
