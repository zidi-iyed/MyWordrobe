package tn.esprit.mywardrobe

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import tn.esprit.mywardrobe.adapter.ClothesAdapter

class ClothPopup(
    private val adapter: ClothesAdapter,
    private val currentCloth: ClothesModel
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_clothes_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView) {
        if (currentCloth.liked) {
            button.setImageResource(R.drawable.ic_star)
        }
        else {
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupStarButton() {
        // recuperer
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        // interaction
        starButton.setOnClickListener {
            currentCloth.liked = !currentCloth.liked
            val repo =ClothesRepository()
            repo.updateCloth(currentCloth)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            // supprimer vetement de la bbd
            val repo = ClothesRepository()
            repo.deleteCloth(currentCloth)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            // fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualiser l'image de vetement
        val clothImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentCloth.imageUrl)).into(clothImage)

        // actualiser le nom de vetement
        findViewById<TextView>(R.id.popup_clothes_name).text = currentCloth.name

        // actualiser la description de vetement
        findViewById<TextView>(R.id.popup_clothes_description_subtitle).text = currentCloth.description

        // actualiser le type de vetement
        findViewById<TextView>(R.id.popup_clothes_type_subtitle).text = currentCloth.type

        // actualiser la saison de vetement
        findViewById<TextView>(R.id.popup_clothes_season_subtitle).text = currentCloth.season
    }

}