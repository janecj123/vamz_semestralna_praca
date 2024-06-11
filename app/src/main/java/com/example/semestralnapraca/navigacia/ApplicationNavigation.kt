package com.example.semestralnapraca.navigacia

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.semestralnapraca.obrazovky.PonukaRestauracieDestination
import com.example.semestralnapraca.obrazovky.PridavanieRestauracieDestination
import com.example.semestralnapraca.obrazovky.PridavanieTovaruDestination
import com.example.semestralnapraca.obrazovky.PrihlasovaciaObrazovka
import com.example.semestralnapraca.obrazovky.PrihlasovanieDestination
import com.example.semestralnapraca.obrazovky.RegistraciaDestination
import com.example.semestralnapraca.obrazovky.RestauracieDestination
import com.example.semestralnapraca.obrazovky.hlavnaObrazovka
import com.example.semestralnapraca.obrazovky.oknoRegistracie
import com.example.semestralnapraca.obrazovky.ponukaRestauracieObrazovka
import com.example.semestralnapraca.obrazovky.pridavanieRestauraciiObrazovka
import com.example.semestralnapraca.obrazovky.pridavanieTovaruObrazovka


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
                onLoginClick = { navController.navigate(RestauracieDestination.route) }
            )
            BackHandler {
            }
        }
        composable(route = RegistraciaDestination.route) {
            oknoRegistracie(
                onSaveClick = { navController.navigate(RestauracieDestination.route) },

                )
            BackHandler {
                navController.navigate(PrihlasovanieDestination.route) {
                    popUpTo(RegistraciaDestination.route) { inclusive = true }
                }
            }
        }
        composable(route = RestauracieDestination.route) {
            hlavnaObrazovka(onItemClick = { navController.navigate(PonukaRestauracieDestination.route) },
                onAddClick = { navController.navigate(PridavanieRestauracieDestination.route) })
            BackHandler {
                navController.navigate(PrihlasovanieDestination.route) {
                    popUpTo(RestauracieDestination.route) { inclusive = true }
                }

            }
        }
        composable(route = PridavanieRestauracieDestination.route) {
            pridavanieRestauraciiObrazovka(onAddClick = {
                navController.navigate(
                    RestauracieDestination.route
                )
            })
            BackHandler {
                navController.navigate(RestauracieDestination.route) {
                    popUpTo(PridavanieRestauracieDestination.route) { inclusive = true }
                }
            }
        }
        composable(route = PonukaRestauracieDestination.route) {
            ponukaRestauracieObrazovka(onAddClick = {
                navController.navigate(
                    PridavanieTovaruDestination.route
                )
            })

            BackHandler {
                navController.navigate(RestauracieDestination.route) {
                    popUpTo(PonukaRestauracieDestination.route) { inclusive = true }
                }
            }
        }
        composable(route = PridavanieTovaruDestination.route) {
            pridavanieTovaruObrazovka(onSaveClick = {
                navController.navigate(
                    PonukaRestauracieDestination.route
                )
            })
            BackHandler {
                navController.navigate(PonukaRestauracieDestination.route) {
                    popUpTo(PridavanieTovaruDestination.route) { inclusive = true }
                }
            }
        }
    }
}
