package data

import java.util.Calendar

class Objednavka(val podnik : Podnik, var hodnotenie : Double = 0.0, var cena : Double = 0.0,
                 var dovezena : Boolean = false, var zoznamObjednavky : MutableList<Tovar> = mutableListOf(),
    var objednana : Boolean = false) {

    fun pridajDoObjednavky(tovar : Tovar){
        zoznamObjednavky.add(tovar)
        cena += tovar.cena
    }

    fun odoberZobjednavky(index : Int){
        zoznamObjednavky.removeAt(index)
    }

    fun ohodnotObjednavku(hodnotenieObjednavky : Double) : Boolean{
        if (hodnotenie in 1.0..5.0) {
            podnik.hodnotenie = (podnik.hodnotenie + hodnotenieObjednavky) / 2
            return true
        }
        return false
    }

    //dorobit funkciu pre platby s kartou alebo v hotovosit
    fun objednaj() : Boolean{
        if (!objednana){
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val aktualnaHodina = hour.toInt()
            val aktualnaMinuta = minute.toInt()
            if (aktualnaHodina <= podnik.zatvaraciaHodina && aktualnaMinuta <= podnik.zatvaraciaMinuta){
                objednana = true;
                return true;
            }

        }
        return false;
    }

    fun vypisObjednavku(){
        for (elements in zoznamObjednavky)
        {
            elements.vypisInfo()
        }
    }

}