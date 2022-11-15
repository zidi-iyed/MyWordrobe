package tn.esprit.mywardrobe.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.esprit.mywardrobe.*

class ClothesAdapter(
    val context: MainActivity,
    private val clothesList : List<ClothesModel>,
    private val layoutId: Int) : RecyclerView.Adapter<ClothesAdapter.ViewHolder>(){

    // boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val clothesImage: ImageView = view.findViewById(R.id.image_item)
        val clothesName:TextView? = view.findViewById(R.id.name_item)
        val clothesDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon: ImageView = view.findViewById(R.id.star_icon)
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

        // recuperer le repository
        val repo = ClothesRepository()

        // utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentClothes.imageUrl)).into(holder.clothesImage)

        // mettre à jour le nom de la vetement
        holder.clothesName?.text = currentClothes.name

        // mettre à jour la description de vetement
        holder.clothesDescription?.text  = currentClothes.description

        // verifier si vetement a été liké
        if(currentClothes.liked){
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        // rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener {
            // inverser si le bouton est like ou non
            currentClothes.liked =!currentClothes.liked
            // mettre à jour l'objet cloth
            repo.updateCloth(currentClothes)
        }

        // interaction lors de click sur vetement
        holder.itemView.setOnClickListener {
            // afficher la popup
            ClothPopup(this, currentClothes).show()
        }

    }

    override fun getItemCount(): Int = clothesList.size
}