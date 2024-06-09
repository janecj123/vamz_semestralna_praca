package com.example.semestralnapraca.navigacia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.semestralnapraca.obrazovky.PrihlasovaciaObrazovka
import com.example.semestralnapraca.obrazovky.PrihlasovanieDestination
import com.example.semestralnapraca.obrazovky.RegistraciaDestination
import com.example.semestralnapraca.obrazovky.RestauracieDestination
import com.example.semestralnapraca.obrazovky.hlavnaObrazovka


import com.example.semestralnapraca.obrazovky.oknoRegistracie
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel
import java.util.Collections.list


@Composable
    fun AplikaciaNavigacia(
        navController: NavHostController,
        modifier: Modifier = Modifier,
    ) {
    NavHost(
        navController = navController,
        startDestination = PrihlasovanieDestination.route,
        modifier = modifier
    ) {
        composable(route = PrihlasovanieDestination.route) {
            PrihlasovaciaObrazovka(
                onRegistrationClick = { navController.navigate(RegistraciaDestination.route) },
                onLoginClick = { navController.navigate("${RestauracieDestination.route}/${it}") }
            )
        }
        composable(route = RegistraciaDestination.route) {
            oknoRegistracie(
                onSaveClick = { navController.navigate(PrihlasovanieDestination.route) },
            )
        }
        composable(route = RestauracieDestination.route) {
            hlavnaObrazovka(onItemClick = {}, onAddClick = { /*TODO*/ })
        }
    }
}
