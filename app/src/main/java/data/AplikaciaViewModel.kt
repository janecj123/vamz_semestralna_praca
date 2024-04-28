package data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AplikaciaViewModel : ViewModel() {

    private val _aktualnyStav = MutableStateFlow(AktualnyStav())
    val aktualnyStav: StateFlow<AktualnyStav> = _aktualnyStav.asStateFlow()

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