package com.example.semestralnapraca.view_modely

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.restauracia.Restauracia
import data.tovar.Tovar
import data.tovar.TovarRepositoryInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PonukaRestauracieViewModel(private val tovarRepositoryInterface: TovarRepositoryInterface): ViewModel() {

    val tovarUiState: StateFlow<TovarUiState> =
        tovarRepositoryInterface.getZoznamTovaru().map { TovarUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(PonukaRestauracieViewModel.TIMEOUT_MILLIS),
                initialValue = TovarUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun vyberTovarResrauracie(zoznamTovaru: List<Tovar>): List<Tovar> {
        var filtrovanyZoznam: MutableList<Tovar> = mutableListOf()
        for (tovar in zoznamTovaru) {
            if (tovar.restauraciaKtorejPatri == VybrataRestauracia.nazov) {
                filtrovanyZoznam.add(tovar)
            }
        }
        return filtrovanyZoznam.toList()

    }
}

data class TovarUiState(val tovarList: List<Tovar> = listOf())