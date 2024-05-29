package com.example.semestralnapraca

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.semestralnapraca.obrazovky.PrihlasovaciaObrazovkaViewModel
import data.PouzivatelRepositoryInterface


object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            PrihlasovaciaObrazovkaViewModel(
                thisApplication().container.pouzivatelRepositoryInterface
            )
        }
    }
}


fun CreationExtras.thisApplication(): EFYBApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EFYBApplication)