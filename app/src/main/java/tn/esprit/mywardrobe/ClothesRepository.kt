package tn.esprit.mywardrobe

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tn.esprit.mywardrobe.ClothesRepository.Singleton.clothesList
import tn.esprit.mywardrobe.ClothesRepository.Singleton.databaseRef

class ClothesRepository {

    object Singleton{
        // se connecter à la reference "clothes"
        val databaseRef = FirebaseDatabase.getInstance().getReference("clothes")

        // créer une liste qui va contenir nos clothes
        val clothesList = arrayListOf<ClothesModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les donneées depuis la databaseRef -> liste de vetements
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes
                clothesList.clear()
                // recolter la liste
                for (ds in snapshot.children) {
                    //construire un objet vetement
                    val cloth = ds.getValue(ClothesModel::class.java)

                    //verifier que vetement n'est pas null
                    if(cloth != null) {
                        // ajouter vetement à notre liste
                        clothesList.add(cloth)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    // mettre à jour un objet cloth en bdd
    fun updateCloth(cloth: ClothesModel) = databaseRef.child(cloth.id).setValue(cloth)

    // supprimer vetement de bdd
    fun deleteCloth(cloth:ClothesModel) = databaseRef.child(cloth.id).removeValue()


}