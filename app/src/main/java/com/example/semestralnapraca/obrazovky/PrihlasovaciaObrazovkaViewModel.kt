package com.example.semestralnapraca.obrazovky

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Pouzivatel
import data.PouzivatelRepositoryInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PrihlasovaciaObrazovkaViewModel(pouzivatelRepositoryInterface: PouzivatelRepositoryInterface) : ViewModel() {

    var pouzivatelUiState by mutableStateOf(AktualnyPouzivatel())
        private set
    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */
    val prihlasovanieUiState: StateFlow<PrihlasovanieUiState> =
        pouzivatelRepositoryInterface.getPouzivatelia().map { PrihlasovanieUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PrihlasovanieUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun overPrihlasenie() : Boolean {
        var najdenyPouzivatel: Boolean = false
        for (pouzivatel in PrihlasovanieUiState().itemList) {
            if (pouzivatel.pouzivatel == AktualnyPouzivatel().pouzivatel && pouzivatel.heslo == AktualnyPouzivatel().heslo) {
                AktualnyPouzivatel().vek = pouzivatel.vek
                AktualnyPouzivatel().meno = pouzivatel.meno
                AktualnyPouzivatel().priezvisko = pouzivatel.priezvisko
                AktualnyPouzivatel().cislo = pouzivatel.cislo
                AktualnyPouzivatel().adresa = pouzivatel.adresa

                najdenyPouzivatel = true

            }
        }
        return najdenyPouzivatel;
    }
}

/**
 * Ui State for HomeScreen
 */
data class PrihlasovanieUiState(val itemList: List<Pouzivatel> = listOf())

data class AktualnyPouzivatel(
    var pouzivatel: String = "",
    var heslo: String = "",
    var meno: String = "",
    var priezvisko: String = "",
    var vek: Int = 0,
    var cislo: Int = 0,
    var adresa: String = ""
)



    fun AktualnyPouzivatel.zmenaPrihlasovaciehoMeno(novyPouzivatel: String) {
        AktualnyPouzivatel().pouzivatel = novyPouzivatel
    }

    fun AktualnyPouzivatel.zmenaPrihlasovaciehoHesla(noveHeslo: String) {
        AktualnyPouzivatel().heslo = noveHeslo
    }


//class PrihlasovaciaObrazovkaViewModel(application : Application) : AndroidViewModel(application){
//
//
//    private val pouzivatelRepository : PouzivatelRepository
//     val aktualnyPouzivatelStav = MutableStateFlow(AktualnyPouzivatel())
//    val aktualnyPouzivatelia: Flow<List<Pouzivatel>>
//
//    init {
//        val pouzivatelDao = ApplicationDatabase.getDatabase(application).pouzivatelDao()
//        pouzivatelRepository = PouzivatelRepository(pouzivatelDao)
//        aktualnyPouzivatelia = pouzivatelRepository.getPouzivatelia()
//    }
//
////    val pouzivatelia: StateFlow<AktualnyPouzivatel> =
////        pouzivatelRepository.getPouzivatelia().map { AktualnyPouzivatel() }
////            .stateIn(
////                scope = viewModelScope,
////                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
////                initialValue = AktualnyPouzivatel()
////            )
//    fun zmenaPrihlasovaciehoMeno(meno: String) {
//        aktualnyPouzivatelStav.update { aktualnyStav ->
//            aktualnyStav.copy(
//                pouzivatel = meno
//            )
//        }
//    }
//
//    fun zmenaPrihlasovaciehoHeslo(heslo: String) {
//        aktualnyPouzivatelStav.update { aktualnyStav ->
//            aktualnyStav.copy(
//                heslo = heslo
//            )
//        }
//    }

//    fun overPrihlasenie(zadaneMeno: String, zadaneHeslo: String): Boolean {
//    var najdenyPouzivatel : Boolean = false
//        viewModelScope.launch {
//            viewModelScope.launch {
//                aktualnyPouzivatelia.collect { pouzivateliaList ->
//                    for (pouzivatel in pouzivateliaList) {
//                        if (pouzivatel.pouzivatel == zadaneMeno && pouzivatel.heslo == zadaneHeslo) {
//                            aktualnyPouzivatelStav.update { aktualnyStav ->
//                                aktualnyStav.copy(
//                                    pouzivatel = zadaneMeno,
//                                    heslo = zadaneHeslo,
//                                    meno = pouzivatel.meno,
//                                    vek = pouzivatel.vek,
//                                    cislo = pouzivatel.cislo,
//                                    adresa = pouzivatel.adresa
//                                )
//                            }
//                        najdenyPouzivatel = true
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        return najdenyPouzivatel
//    }
//fun overPrihlasenie(zadaneMeno: String, zadaneHeslo: String): Boolean {
//    var najdenyPouzivatel = false
//
//    runBlocking {
//        aktualnyPouzivatelia.collect { pouzivateliaList ->
//            for (pouzivatel in pouzivateliaList) {
//                if (pouzivatel.pouzivatel == zadaneMeno && pouzivatel.heslo == zadaneHeslo) {
//                    najdenyPouzivatel = true
//                    break
//                }
//            }
//        }
//    }
//
//    return najdenyPouzivatel
//}
//        fun registracia() {
//
//        }

//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
//}

//data class PouzivateliaUIState(val itemList: List<Pouzivatel> = listOf())