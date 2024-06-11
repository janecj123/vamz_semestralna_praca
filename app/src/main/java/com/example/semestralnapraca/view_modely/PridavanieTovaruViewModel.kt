package com.example.semestralnapraca.view_modely


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.restauracia.Restauracia
import data.tovar.Tovar
import data.tovar.TovarRepositoryInterface
import kotlinx.coroutines.launch

class PridavanieTovaruViewModel(private val tovarRepositoryInterface: TovarRepositoryInterface) : ViewModel() {

    var pridavanyTovarUiState by mutableStateOf(PridavanyTovar())


    fun pridajTovar(nazovRestauracie: String) : Boolean {

        if (pridavanyTovarUiState.nazov.isNotEmpty() || pridavanyTovarUiState.popis.isNotEmpty() ||
            pridavanyTovarUiState.cena.isNotEmpty()
            || pridavanyTovarUiState.vaha.isNotEmpty()){
            var aktualnyTovar = pridavanyTovarUiState.toTovar(nazovRestauracie)
            viewModelScope.launch {
                tovarRepositoryInterface.insertTovar(aktualnyTovar)
            }
            return true
        }
        return false
    }


    fun zmenaNazvu(nazov: String) {
        pridavanyTovarUiState = pridavanyTovarUiState.copy(nazov = nazov)
    }

    fun znemaPopisu(popis: String) {
        pridavanyTovarUiState = pridavanyTovarUiState.copy(popis = popis)
    }

    fun zmenaCeny(cena: String) {
        pridavanyTovarUiState = pridavanyTovarUiState.copy(cena = cena)
    }

    fun zmenaVahy(vaha: String) {
        pridavanyTovarUiState = pridavanyTovarUiState.copy(vaha = vaha)
    }

}



data class PridavanyTovar(
    var nazov: String = "",
   var popis: String = "",
    var cena: String = "",
    var vaha: String = ""
) {
    fun toTovar(nazovRestauracia: String) : Tovar {
        return Tovar(
            nazov = nazov,
            popis = popis,
            cena = cena.toDouble(),
            vaha = vaha.toInt(),
            restauraciaKtorejPatri = nazovRestauracia
        )
    }
}