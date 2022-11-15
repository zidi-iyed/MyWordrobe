package tn.esprit.mywardrobe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.mywardrobe.ClothesRepository.Singleton.clothesList
import tn.esprit.mywardrobe.MainActivity
import tn.esprit.mywardrobe.R
import tn.esprit.mywardrobe.adapter.ClothesAdapter
import tn.esprit.mywardrobe.adapter.ClothesItemDecoration

class CollectionFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        // recuperer recyclerview
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = ClothesAdapter(context, clothesList.filter { it.liked }, R.layout.item_vertical_clothes)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(ClothesItemDecoration())

        return view
    }
}