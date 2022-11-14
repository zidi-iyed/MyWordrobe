package tn.esprit.mywardrobe.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.esprit.mywardrobe.ClothesModel
import tn.esprit.mywardrobe.MainActivity
import tn.esprit.mywardrobe.R

class ClothesAdapter(
    private val context: MainActivity,
    private val clothesList : List<ClothesModel>,
    private val layoutId: Int) : RecyclerView.Adapter<ClothesAdapter.ViewHolder>(){

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations de la vetement
        val currentClothes = clothesList[position]

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentClothes.imageUrl)).into(holder.clothesImage)

    }

    override fun getItemCount(): Int = clothesList.size
}