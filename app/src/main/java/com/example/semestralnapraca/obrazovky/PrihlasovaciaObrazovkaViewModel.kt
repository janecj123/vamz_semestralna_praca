package com.example.semestralnapraca.obrazovky

import androidx.lifecycle.ViewModel
import data.Pouzivatel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PrihlasovaciaObrazovkaViewModel : ViewModel() {

    private val _aktualnyStav = MutableStateFlow(Pouzivatel())
    val aktualnyStav: StateFlow<Pouzivatel> = _aktualnyStav.asStateFlow()

    fun zmenaPrihlasovaciehoMeno(meno: String){
        _aktualnyStav.update { aktualnyStav -> aktualnyStav.copy(
            pouzivatel = meno
        ) }
    }
    fun zmenaPrihlasovaciehoHeslo(heslo: String) {
        _aktualnyStav.update { aktualnyStav ->
            aktualnyStav.copy(
                heslo = heslo
            )
        }
    }
    fun overPrihlasenie(){


    }
}