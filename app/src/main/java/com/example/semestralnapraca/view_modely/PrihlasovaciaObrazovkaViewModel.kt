package com.example.semestralnapraca.view_modely

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.pouzivatel.Pouzivatel
import data.pouzivatel.PouzivatelDatabase
import data.pouzivatel.PouzivatelRepositoryInterface
import data.restauracia.RestauraciaDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PrihlasovaciaObrazovkaViewModel(savedStateHandle: SavedStateHandle,
                                      private val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface
) : ViewModel() {

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


    fun zmenaPrihlasovaciehoMena(novyPouzivatel: String) {
        pouzivatelUiState = pouzivatelUiState.copy(pouzivatel = novyPouzivatel)

    }

    fun zmenaPrihlasovaciehoHesla(noveHeslo: String) {
        pouzivatelUiState = pouzivatelUiState.copy(heslo = noveHeslo)
        zasifrujHeslo()
    }

    fun zmenMeno(noveMeno: String) {
        pouzivatelUiState = pouzivatelUiState.copy(meno = noveMeno)
    }

    fun zmenPriezvisko(novePriezvisko: String) {
        pouzivatelUiState = pouzivatelUiState.copy(priezvisko = novePriezvisko)
    }

    fun zmenEmail(noveEmail: String) {
        pouzivatelUiState = pouzivatelUiState.copy(email = noveEmail)
    }

    fun zmenVek(novyVek: String) {
        pouzivatelUiState = pouzivatelUiState.copy(vek = novyVek)
    }

    fun zmenAdresu(noveAdresa: String) {
        pouzivatelUiState = pouzivatelUiState.copy(adresa = noveAdresa)
    }

    fun zmenCislo(noveCislo: String) {
        pouzivatelUiState = pouzivatelUiState.copy(cislo = noveCislo)
    }

    fun getMenoAPriezvisko(): String {
        return pouzivatelUiState.meno + " " + pouzivatelUiState.priezvisko
    }
    fun getVekAsString(): String{
        return pouzivatelUiState.vek.toString()
    }

    fun getPouzivatelia(): List<Pouzivatel> {
        // Použite runBlocking na spustenie korutíny v synchronnej metóde
        return runBlocking {
            prihlasovanieUiState.first().pouzivateliaList
        }
    }
    fun zasifrujHeslo() {
        val zasifrovaneHeslo = StringBuilder()
        for (znak in pouzivatelUiState.heslo) {
            zasifrovaneHeslo.append("*")
        }
        pouzivatelUiState = pouzivatelUiState.copy(sifrovaneHeslo = zasifrovaneHeslo.toString())
    }

  fun overPrihlasenie(pouzivateliaList:List<Pouzivatel>): Boolean {


      var najdenyPouzivatel = false

          for (pouzivatel in pouzivateliaList) {
              if (pouzivatel.pouzivatel == pouzivatelUiState.pouzivatel && pouzivatel.heslo == pouzivatelUiState.heslo) {
                  PrihlasenyPouzivatel.vek = pouzivatel.vek.toString()
                  PrihlasenyPouzivatel.meno = pouzivatel.meno
                  PrihlasenyPouzivatel.priezvisko = pouzivatel.priezvisko
                  PrihlasenyPouzivatel.cislo = pouzivatel.cislo
                  PrihlasenyPouzivatel.adresa = pouzivatel.adresa

                  najdenyPouzivatel = true
                  return najdenyPouzivatel
              }
          }

      return najdenyPouzivatel;

    }


    fun overRegistraciu(): Boolean {
        var najdenyPouzivatel = false
        for (pouzivatel in PrihlasovanieUiState().pouzivateliaList) {
            if (pouzivatel.pouzivatel == pouzivatelUiState.pouzivatel) {
                najdenyPouzivatel = true
            }
        }
        return najdenyPouzivatel
    }

    fun ulozPouzivatela() {
        viewModelScope.launch {
            pouzivatelRepositoryInterface.insertPouzivatel(pouzivatelUiState.toPouzivatel())
        }
    }


}




/**
 * Ui State for HomeScreen
 */
data class PrihlasovanieUiState(val pouzivateliaList: List<Pouzivatel> = listOf())

data class AktualnyPouzivatel(
    var pouzivatel: String = "",
    var heslo: String = "",
    var meno: String = "",
    var priezvisko: String = "",
    var vek: String = "0",
    var cislo: String = "",
    var adresa: String = "",
    var sifrovaneHeslo: String = "",
    var email: String = "",
    var zostatok: Double = 0.0
) {

    fun toPouzivatel(): Pouzivatel {
        return Pouzivatel(
            pouzivatel = pouzivatel,
            heslo = heslo,
            meno = meno,
            priezvisko = priezvisko,
            vek = vek.toInt(),
            cislo = cislo,
            adresa = adresa,
            email = email,
            zostatok = zostatok
        )

    }
}

class PrihlasenyPouzivatel() {
    companion object  {
        var pouzivatel: String = ""
        var heslo: String = ""
        var meno: String = ""
        var priezvisko: String = ""
        var vek: String = "0"
        var cislo: String = ""
        var adresa: String = ""
        var sifrovaneHeslo: String = ""
        var email: String = ""
        var zostatok: Double = 0.0

        private var Instance: PrihlasenyPouzivatel = PrihlasenyPouzivatel()
        fun getPrihlasenyPouzivatel(): PrihlasenyPouzivatel {

            return Instance
        }

        fun priradPouzivatela(aktualnyPouzivatel: AktualnyPouzivatel) {
            pouzivatel = aktualnyPouzivatel.pouzivatel
            heslo = aktualnyPouzivatel.heslo
            meno = aktualnyPouzivatel.meno
            priezvisko = aktualnyPouzivatel.priezvisko
            vek = aktualnyPouzivatel.vek.toString()
            cislo = aktualnyPouzivatel.cislo
            adresa = aktualnyPouzivatel.adresa
            email = aktualnyPouzivatel.email
        }

    }
}









