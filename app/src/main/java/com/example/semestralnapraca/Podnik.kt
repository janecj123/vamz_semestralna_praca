package com.example.semestralnapraca

import allKindOfGoods.Tovar


class Podnik(var meno: String, var sidlo: String, var otvaraciaHodina : Int  = 8,
             var otvaraciaMinuta : Int  = 0, var zatvaraciaHodina : Int  = 22, var zatvaraciaMinuta : Int  = 0,
             var hdnotenie : Double = 2.5,val ponukaTovaru : MutableList<Tovar> = mutableListOf()) {


    fun pridajTovar(tovar : Tovar){
        ponukaTovaru.add(tovar)
    }

    fun vypisPonuku(){
        for (tovar in ponukaTovaru)
        {
            tovar.vypisInfo()
        }
    }
}

