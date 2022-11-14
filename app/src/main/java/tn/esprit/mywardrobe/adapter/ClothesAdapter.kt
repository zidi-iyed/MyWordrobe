package tn.esprit.mywardrobe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.mywardrobe.R

class ClothesAdapter(private val layoutId: Int) : RecyclerView.Adapter<ClothesAdapter.ViewHolder>(){

    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val clothesImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.
                from(parent.context).
                inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int = 5
}