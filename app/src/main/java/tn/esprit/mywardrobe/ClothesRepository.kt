package tn.esprit.mywardrobe

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import tn.esprit.mywardrobe.ClothesRepository.Singleton.clothesList
import tn.esprit.mywardrobe.ClothesRepository.Singleton.databaseRef
import tn.esprit.mywardrobe.ClothesRepository.Singleton.downloadUri
import tn.esprit.mywardrobe.ClothesRepository.Singleton.storageReference
import java.util.UUID
import kotlin.coroutines.Continuation

class ClothesRepository {

    object Singleton{
        // donner le lien pour acceder au buket
        private val BUKET_URL: String = "gs://wardrobecollection-65762.appspot.com"

        // se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUKET_URL)

        // se connecter à la reference "clothes"
        val databaseRef = FirebaseDatabase.getInstance().getReference("clothes")

        // créer une liste qui va contenir nos clothes
        val clothesList = arrayListOf<ClothesModel>()

        // contenir le lien de l'image courant
        var downloadUri: Uri? = null
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

    // créer une fonction pour envoyer des fichier au storage
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // verifier que ce fichier est non null
        if (file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            // demarer la tache d'envoi
            uploadTask.continueWithTask(com.google.android.gms.tasks.Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->

                // si il y a eu un pb lors de l'envoi du fichier
                if(!task.isSuccessful) {
                    task.exception?.let { throw it }
                }

                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                // verifier si tout a bien fonctionné
                if(task.isSuccessful) {
                    // recuperer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // mettre à jour un objet cloth en bdd
    fun updateCloth(cloth: ClothesModel) = databaseRef.child(cloth.id).setValue(cloth)

    // inserer un nouvel vetement en bdd
    fun insertCloth(cloth: ClothesModel) = databaseRef.child(cloth.id).setValue(cloth)

    // supprimer vetement de bdd
    fun deleteCloth(cloth:ClothesModel) = databaseRef.child(cloth.id).removeValue()


}