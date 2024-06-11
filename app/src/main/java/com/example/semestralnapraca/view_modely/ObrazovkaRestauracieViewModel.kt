package com.example.semestralnapraca.view_modely

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.restauracia.Restauracia
import data.restauracia.RestauraciaRepositoryInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// trieda udrziava stav vsetkych restauracii  ktore su v databaze
class ObrazovkaRestauracieViewModel(
    private val restauracieRepositoryInterface: RestauraciaRepositoryInterface,

    ) : ViewModel() {

    var prihlasenyPouzivatelCenaObjednavkyState by mutableDoubleStateOf(PrihlasenyPouzivatel.cenaObjednavky)
        private set

    val restauracieUiState: StateFlow<RestauracieUiState> =
        restauracieRepositoryInterface.getZoznamRestauracii().map { RestauracieUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RestauracieUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


    fun pripocitajJedlo(cena: Double) {
        prihlasenyPouzivatelCenaObjednavkyState += cena
        PrihlasenyPouzivatel.cenaObjednavky += cena
    }

}

data class RestauracieUiState(val restauracieList: List<Restauracia> = listOf())

class VybrataRestauracia() {
    companion object {
        var nazov: String = ""

        private var Instance: VybrataRestauracia = VybrataRestauracia()
        fun getVybrataRestauracia(): VybrataRestauracia {

            return Instance
        }
    }
}