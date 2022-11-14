package tn.esprit.mywardrobe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.mywardrobe.ClothesModel
import tn.esprit.mywardrobe.MainActivity
import tn.esprit.mywardrobe.R
import tn.esprit.mywardrobe.adapter.ClothesAdapter
import tn.esprit.mywardrobe.adapter.ClothesItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // créer une liste qui va stocker ces vetements
        val clothesList = arrayListOf<ClothesModel>()

        // enregistrer une premier vetemet dans notre liste (Veste)
        clothesList.add(
            ClothesModel(
                "Veste",
                "VESTE DE CHASSE HUILÉE BEDALE BARBOUR",
                "https://i.ibb.co/d2Yg2kj/veste.jpg",
                false
        ))

        // enregistrer une second vetemet dans notre liste (Pantalon)
        clothesList.add(
            ClothesModel(
                "Pantalon",
                "PANTALON VELOURS MAYFAIR LAKSEN",
                "https://i.ibb.co/5KGhY6t/pantalon.jpg",
                false
            ))

        // enregistrer une troisieme vetemet dans notre liste (Chaussures)
        clothesList.add(
            ClothesModel(
                "Chaussures",
                "CHAUSSURES SAXNÄS GTX HÄRKILA",
                "https://i.ibb.co/r6WxLPD/chaussures.jpg",
                false
            ))

        //recuperer le recuclerview
        val horizantalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizantalRecyclerView.adapter = ClothesAdapter(context, clothesList, R.layout.item_horizontal_clothes)

        // recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ClothesAdapter(context, clothesList, R.layout.item_vertical_clothes)
        verticalRecyclerView.addItemDecoration(ClothesItemDecoration())

        return view
    }
}