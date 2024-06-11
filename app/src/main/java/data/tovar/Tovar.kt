package data.tovar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tovar(
    @PrimaryKey(autoGenerate = true)
    var tovarID: Int = 0,
    var nazov: String = " ",
    var popis: String = " ",
    var cena: Double = 0.0,
    var vaha: Int = 0,
    var restauraciaKtorejPatri: String = ""
)