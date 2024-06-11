package com.example.semestralnapraca.view_modely

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.restauracia.Restauracia
import data.restauracia.RestauraciaRepositoryInterface
import kotlinx.coroutines.launch

//trieda sluzi na pridavanie novej restauracie, udrziava v sebe stav pridavanej restauracie
//ak je restauracia pripravana na pridanie prida ju do databazy
class PridavanieRestauraciiViewModel(private val restauracieRepositoryInterface: RestauraciaRepositoryInterface) :
    ViewModel() {

    var pridavanaRestauraciaUiState by mutableStateOf(PridavanaRestauracia())

    fun pridajRestauraciu(): Boolean {
        var aktualnaRestauracia = pridavanaRestauraciaUiState.toRestauracia()
        if (aktualnaRestauracia.nazov.isNotEmpty() || aktualnaRestauracia.adresa.isNotEmpty()
            || aktualnaRestauracia.cislo.isNotEmpty() || aktualnaRestauracia.email.isNotEmpty()
            || aktualnaRestauracia.webovaStranka.isNotEmpty()
        ) {
            viewModelScope.launch {
                restauracieRepositoryInterface.insertRestauracia(aktualnaRestauracia)
            }
            return true
        }
        return false
    }

    fun zmenaNazvu(nazov: String) {
        pridavanaRestauraciaUiState = pridavanaRestauraciaUiState.copy(nazov = nazov)
    }

    fun zmenaAdresy(adresa: String) {
        pridavanaRestauraciaUiState = pridavanaRestauraciaUiState.copy(adresa = adresa)
    }

    fun zmenaCisla(cislo: String) {
        pridavanaRestauraciaUiState = pridavanaRestauraciaUiState.copy(cislo = cislo)
    }

    fun zmenaEmailu(email: String) {
        pridavanaRestauraciaUiState = pridavanaRestauraciaUiState.copy(email = email)
    }

    fun zmenaWebovejStranky(webovaStranka: String) {
        pridavanaRestauraciaUiState =
            pridavanaRestauraciaUiState.copy(webovaStranka = webovaStranka)
    }

}


data class PridavanaRestauracia(
    var nazov: String = "",
    var adresa: String = "",
    var cislo: String = "",
    var email: String = "",
    var webovaStranka: String = ""
) {
    fun toRestauracia(): Restauracia {
        return Restauracia(
            nazov = nazov,
            adresa = adresa,
            cislo = cislo,
            email = email,
            webovaStranka = webovaStranka
        )
    }
}