package clandestino.giphymvvmapp.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun RecyclerView.getViewItemDecoration(): RecyclerView.ItemDecoration {
    return object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams

            val spanIndex = layoutParams.spanIndex
            val index = parent.getChildLayoutPosition(view)
            val itemCount = parent.adapter?.itemCount ?: 0

            when (spanIndex) {
                0 -> {
                    outRect.left = 16
                    outRect.right = 8
                }
                1 -> {
                    outRect.left = 8
                    outRect.right = 16
                }
            }

            if (index < 2) {
                outRect.top = 16
                outRect.bottom = 8
            } else if (index == itemCount - 1) {
                outRect.top = 8
                outRect.bottom = 16
            } else if (index == itemCount - 2 && layoutParams.isFullSpan) {
                outRect.top = 8
                outRect.bottom = 16
            } else {
                outRect.top = 8
                outRect.bottom = 8
            }
        }
    }
}