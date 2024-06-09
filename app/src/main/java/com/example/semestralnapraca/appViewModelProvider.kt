package com.example.semestralnapraca

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.semestralnapraca.view_modely.ObrazovkaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            PrihlasovaciaObrazovkaViewModel(
                this.createSavedStateHandle(),
                thisApplication().container.pouzivatelRepositoryInterface
            )
        }
        initializer {
            ObrazovkaRestauracieViewModel(
                this.createSavedStateHandle(),
                thisApplication().container.restauraciaRepositoryInterface
            )
        }
    }
}


fun CreationExtras.thisApplication(): EFYBApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EFYBApplication)