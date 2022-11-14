package tn.esprit.mywardrobe.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ClothesItemDecoration : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 20
    }
}