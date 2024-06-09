package data.restauracia

import androidx.room.Entity
import androidx.room.PrimaryKey
import data.tovar.Tovar

@Entity
data class Restauracia(
    @PrimaryKey(autoGenerate = true)
    var restauraciaID: Int = 0,
    var nazov: String = "",
    var adresa: String = "",
    var cislo: String = "",
    var email: String = "",
    var webovaStranka: String = ""

    //var ponuka: List<Tovar> = emptyList()
)