package tn.esprit.mywardrobe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.mywardrobe.R
import tn.esprit.mywardrobe.adapter.ClothesAdapter
import tn.esprit.mywardrobe.adapter.ClothesItemDecoration

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //recuperer le recuclerview
        val horizantalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizantalRecyclerView.adapter = ClothesAdapter(R.layout.item_horizontal_clothes)

        // recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ClothesAdapter(R.layout.item_vertical_clothes)
        verticalRecyclerView.addItemDecoration(ClothesItemDecoration())

        return view
    }
}