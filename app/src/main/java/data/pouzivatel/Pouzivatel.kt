package data.pouzivatel
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Pouzivatel(
    @PrimaryKey()
    var pouzivatel: String=" ",
    var heslo : String =" ",
    var meno: String = " ",
    var priezvisko: String = " ",
    var vek: Int = 0,
    var cislo: String = "",
    var adresa: String = "",
    var zostatok: Double = 0.0,
    var email: String = ""
)

