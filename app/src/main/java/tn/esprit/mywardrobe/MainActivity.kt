package tn.esprit.mywardrobe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tn.esprit.mywardrobe.fragments.AddClothFragment
import tn.esprit.mywardrobe.fragments.CollectionFragment
import tn.esprit.mywardrobe.fragments.HomeFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        // importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this), R.string.favorites_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_cloth_page -> {
                    loadFragment(AddClothFragment(this), R.string.add_clothes_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre ClothesRepository
        val repo = ClothesRepository()

        // actualiser le ttire de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        // metre à jour la liste de vetements
        repo.updateData{
            // injecte le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}