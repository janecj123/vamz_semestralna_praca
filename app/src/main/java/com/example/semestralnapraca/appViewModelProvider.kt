package com.example.semestralnapraca

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.semestralnapraca.view_modely.ObrazovkaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PonukaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PridavanieRestauraciiViewModel
import com.example.semestralnapraca.view_modely.PridavanieTovaruViewModel
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel


// drzi v sebe factory ktora vytvara instancie viewmodelov pre celu aplikaciu
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            PrihlasovaciaObrazovkaViewModel(
                thisApplication().container.pouzivatelRepositoryInterface
            )
        }
        initializer {
            ObrazovkaRestauracieViewModel(
                thisApplication().container.restauraciaRepositoryInterface
            )
        }
        initializer {
            PridavanieRestauraciiViewModel(
                thisApplication().container.restauraciaRepositoryInterface
            )
        }
        initializer {
            PonukaRestauracieViewModel(
                thisApplication().container.tovarRepositoryInterface
            )
        }
        initializer {
            PridavanieTovaruViewModel(
                thisApplication().container.tovarRepositoryInterface
            )
        }
    }
}


fun CreationExtras.thisApplication(): EFYBApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EFYBApplication)