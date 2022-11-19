package tn.esprit.mywardrobe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.mywardrobe.fragments.AddClothFragment
import tn.esprit.mywardrobe.fragments.CollectionFragment
import tn.esprit.mywardrobe.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // charger notre ClothesRepository
        val repo = ClothesRepository()

        // metre à jour la liste de vetements
        repo.updateData{
            // injecte le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}