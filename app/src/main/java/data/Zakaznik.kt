package data

open class Zakaznik(val meno : String, val priezvisko : String, var vek : Int,var cislo:String,
    var adresa : String, var Heslo : String,var objednavky : MutableList<Objednavka> = mutableListOf(),
    var aktualnaObjednavka : Objednavka? = null) {

    fun vytvorObjednavku( podnik: Podnik){
    var objednavka = Objednavka(podnik)
    objednavky.add(objednavka)
    aktualnaObjednavka = objednavka
    }

    fun pridajDoObjednavky(tovar: Tovar) {
        try {
            aktualnaObjednavka!!.pridajDoObjednavky(tovar)
        } catch (n: NullPointerException) {
            println("Tvoja objendavka este nexistuje")

        }

    }

    fun vypisObjednavku(index: Int){
        if (index < objednavky.size)
        {
            objednavky[index].vypisObjednavku()
        }
    }
}