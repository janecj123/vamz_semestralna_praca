package com.example.semestralnapraca.view_modely

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.pouzivatel.Pouzivatel
import data.restauracia.Restauracia
import data.restauracia.RestauraciaRepositoryInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ObrazovkaRestauracieViewModel(savedStateHandle: SavedStateHandle,
                                    private val restauracieRepositoryInterface: RestauraciaRepositoryInterface) : ViewModel() {


    var aktualnyPouzivatel: Pouzivatel = AktualnyPouzivatel().toPouzivatel()

    val restauracieUiState: StateFlow<RestauracieUiState> =
        restauracieRepositoryInterface.getZoznamRestauracii().map { RestauracieUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(ObrazovkaRestauracieViewModel.TIMEOUT_MILLIS),
                initialValue = RestauracieUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun naplnDatabazu() {
        viewModelScope.launch {
            restauracieRepositoryInterface.insertRestauracia(
                Restauracia(
                    1,
                    "Pizzeria VIOLA",
                    "Hlavna 1, Bratislava",
                    "+421 123 456 789",
                    "info@restauracia1.sk",
                    "www.restauracia1.sk"
                )
            )

        }

    }
}



data class RestauracieUiState(val restauracieList: List<Restauracia> = listOf())