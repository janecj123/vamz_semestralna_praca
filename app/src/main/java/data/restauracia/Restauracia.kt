package data.restauracia

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Restauracia(
    @PrimaryKey(autoGenerate = true)
    var restauraciaID: Int = 0,
    var nazov: String = "",
    var adresa: String = "",
    var cislo: String = "",
    var email: String = "",
    var webovaStranka: String = ""
)