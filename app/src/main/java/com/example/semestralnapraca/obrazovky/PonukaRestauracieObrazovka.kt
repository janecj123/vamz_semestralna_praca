package com.example.semestralnapraca.obrazovky

import androidx.annotation.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource


import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.AplikaciaTopBar
import com.example.semestralnapraca.AppViewModelProvider
import com.example.semestralnapraca.R
import com.example.semestralnapraca.navigacia.NavigationDestination
import com.example.semestralnapraca.view_modely.ObrazovkaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PonukaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PrihlasenyPouzivatel

import data.restauracia.Restauracia
import data.tovar.Tovar

object PonukaRestauracieDestination : NavigationDestination {
    override val route = "ponukaRestauracie"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ponukaRestauracieObrazovka(modifier: Modifier = Modifier, ponukaRestauracieViewModel: PonukaRestauracieViewModel
= viewModel(factory = AppViewModelProvider.Factory), onAddClick: () -> Unit, onItemClick : ()  -> Unit) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val tovarUiState by ponukaRestauracieViewModel.tovarUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AplikaciaTopBar(
                pouzivatel = PrihlasenyPouzivatel.meno + " " + PrihlasenyPouzivatel.priezvisko,
                mozePridavat = true,
                scrollBehavior = scrollBehavior,
                navigujDoPridavania = onAddClick
            )


        },

        ) { innerPadding ->
        zoznamTovaru(
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize(),
            contentPadding = innerPadding,
            onItemClick = onItemClick,
            zoznamRestauracie = ponukaRestauracieViewModel.vyberTovarResrauracie(tovarUiState.tovarList)
        )
    }
}

    @Composable
    fun zoznamTovaru(
        modifier: Modifier,
        obrazovkaRestauracieViewModel: ObrazovkaRestauracieViewModel = viewModel(factory = AppViewModelProvider.Factory),
        zoznamRestauracie: List<Tovar>,
        contentPadding: PaddingValues = PaddingValues(0.dp),
        onItemClick: () -> Unit = {}
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = modifier,
            contentPadding = contentPadding,
        ) {
            items(
                items = zoznamRestauracie,
                key = { tovar -> tovar.tovarID }) { tovar ->
                tovarRozlozenie(tovar = tovar, modifier = Modifier

                    .clickable {
                        onItemClick()
                    })

            }


        }
    }
        @Composable
         fun tovarRozlozenie(tovar:Tovar, modifier:Modifier) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
            {

                Column( modifier = modifier.height(80.dp),verticalArrangement = Arrangement.Bottom
                    ) {
                    Text(text = tovar.vaha.toString()  + "g",
                        color= Color.Black,
                        textAlign = TextAlign.Left,
                        )
                }
                Column(modifier = modifier.height(80.dp),
                    ) {

                    Column(modifier = modifier.height(35.dp)
                        .width(200.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = tovar.nazov,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,

                            )

                    }

                        Column(modifier = modifier.height(45.dp)
                            .width(200.dp),
                            verticalArrangement = Arrangement.Bottom) {
                            Text(
                                text = stringResource(R.string.zlozenie) + tovar.popis,
                                color = Color.Black,
                                //textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )

                        }

                }

                Column(modifier = modifier.height(80.dp)
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Bottom

                   ) {
                    Text(text = tovar.cena.toString() + "€",
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                        )

                }


            }
            Divider(color = Color.Black, thickness = 1.dp)
        }