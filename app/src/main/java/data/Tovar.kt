package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TovarInfo")
data class Tovar(
    @PrimaryKey()
    var tovarID: Int = 0,
    var meno: String = " ",
    var vaha: Int = 0,
    var cena: Int = 0
)

