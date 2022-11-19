package tn.esprit.mywardrobe.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import tn.esprit.mywardrobe.ClothesModel
import tn.esprit.mywardrobe.ClothesRepository
import tn.esprit.mywardrobe.ClothesRepository.Singleton.downloadUri
import tn.esprit.mywardrobe.MainActivity
import tn.esprit.mywardrobe.R
import java.util.UUID

class AddClothFragment(
    private val context: MainActivity
) : Fragment() {
    private var file:Uri? = null
    private var uploadedImage:ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmetn_add_cloth, container, false)

        // recuperer uploadedImage pour lui associer son composant
        uploadedImage = view.findViewById(R.id.preview_image)

        // recuperer le bouton pour charger l'image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        // lorsqu'on clique dessus ca ouvre les images du tel
        pickupImageButton.setOnClickListener { pickupImage() }

        // recuperer le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            sendForm(view)
            view.invalidate()
        }

        return view
    }

    private fun sendForm(view: View) {
        val repo = ClothesRepository()
        repo.uploadImage(file!!){
            val clothName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val clothDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val clothType = view.findViewById<Spinner>(R.id.type_spinner).selectedItem.toString()
            val clothSeason = view.findViewById<Spinner>(R.id.season_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri

            // créer un nouvel objet vetement
            val cloth = ClothesModel(
                UUID.randomUUID().toString(),
                clothName,
                clothDescription,
                downloadImageUrl.toString(),
                clothType,
                clothSeason
            )

            // envoyer en bdd
            repo.insertCloth(cloth)
        }


    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 47 && resultCode == Activity.RESULT_OK){
            // verfifier si les données sont nulles
            if(data == null || data.data == null) return

            // recuperer l'image selectionner
            file = data.data

            // mettre à jour l'aperçu de l'image
            uploadedImage?.setImageURI(file)
        }
    }
}