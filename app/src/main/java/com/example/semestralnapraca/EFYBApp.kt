package com.example.semestralnapraca

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca.navigacia.AplikaciaNavigacia

@Composable
fun EFYBApp(navController: NavHostController = rememberNavController()) {
    AplikaciaNavigacia(navController = navController)
}