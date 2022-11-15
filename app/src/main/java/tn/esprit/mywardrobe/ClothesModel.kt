package tn.esprit.mywardrobe

class ClothesModel (
    val id : String = "cloth0",
    val name : String = "Tommy Jacket",
    val description : String = "Petite description",
    val imageUrl : String = "https://i.ibb.co/d2Yg2kj/veste.jpg",
    val type: String = "Dessus",
    val season: String = "été",
    var liked : Boolean = false
)