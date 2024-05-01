package data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "PouzivatelInfo")
data class Pouzivatel(
    @PrimaryKey()
    var pouzivatel: String=" ",
    var heslo : String =" ",
    var meno: String = " ",
    var vek: Int = 0,
    var cislo: Int = 0,
    var adresa: String = " "

)
