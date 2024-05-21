package com.example.semestralnapraca.obrazovky

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.AktualnyPouzivatel
import data.ApplicationDatabase
import data.Pouzivatel
import data.PouzivatelRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PrihlasovaciaObrazovkaViewModel(application : Application) : ViewModel() {


    private val pouzivatelRepository : PouzivatelRepository
     val aktualnyPouzivatelStav = MutableStateFlow(AktualnyPouzivatel())
    val aktualnyPouzivatelia: Flow<List<Pouzivatel>>

    init {
        val pouzivatelDao = ApplicationDatabase.getDatabase(application).pouzivatelDao()
        pouzivatelRepository = PouzivatelRepository(pouzivatelDao)
        aktualnyPouzivatelia = pouzivatelRepository.getPouzivatelia()
    }

//    val pouzivatelia: StateFlow<AktualnyPouzivatel> =
//        pouzivatelRepository.getPouzivatelia().map { AktualnyPouzivatel() }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = AktualnyPouzivatel()
//            )
    fun zmenaPrihlasovaciehoMeno(meno: String) {
        aktualnyPouzivatelStav.update { aktualnyStav ->
            aktualnyStav.copy(
                pouzivatel = meno
            )
        }
    }

    fun zmenaPrihlasovaciehoHeslo(heslo: String) {
        aktualnyPouzivatelStav.update { aktualnyStav ->
            aktualnyStav.copy(
                heslo = heslo
            )
        }
    }

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
fun overPrihlasenie(zadaneMeno: String, zadaneHeslo: String): Boolean {
    var najdenyPouzivatel = false

    runBlocking {
        aktualnyPouzivatelia.collect { pouzivateliaList ->
            for (pouzivatel in pouzivateliaList) {
                if (pouzivatel.pouzivatel == zadaneMeno && pouzivatel.heslo == zadaneHeslo) {
                    najdenyPouzivatel = true
                    break
                }
            }
        }
    }

    return najdenyPouzivatel
}
        fun registracia() {

        }

//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
}

//data class PouzivateliaUIState(val itemList: List<Pouzivatel> = listOf())