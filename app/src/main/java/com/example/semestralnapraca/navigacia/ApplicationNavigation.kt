package com.example.semestralnapraca.navigacia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.semestralnapraca.obrazovky.PrihlasovaciaObrazovka
import com.example.semestralnapraca.obrazovky.PrihlasovanieDestination


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
            PrihlasovaciaObrazovka()
        }

    }
}
