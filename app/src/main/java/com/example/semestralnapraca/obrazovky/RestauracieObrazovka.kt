//@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.semestralnapraca.obrazovky

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.AplikaciaTopBar
import com.example.semestralnapraca.AppViewModelProvider
import com.example.semestralnapraca.navigacia.NavigationDestination
import com.example.semestralnapraca.view_modely.ObrazovkaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PrihlasenyPouzivatel
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel
import data.restauracia.Restauracia
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.semestralnapraca.view_modely.VybrataRestauracia

object RestauracieDestination : NavigationDestination {
    override val route = "restauracie"
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hlavnaObrazovka(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    onAddClick: () -> Unit,
    obrazovkaRestauracieViewModel: ObrazovkaRestauracieViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val restauracieUiState by obrazovkaRestauracieViewModel.restauracieUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()




    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AplikaciaTopBar(
                pouzivatel = PrihlasenyPouzivatel.meno + " " + PrihlasenyPouzivatel.priezvisko,
                mozePridavat = true,
                scrollBehavior = scrollBehavior,
                navigujDoPridavania = onAddClick,
                cenaObjednavky = obrazovkaRestauracieViewModel.prihlasenyPouzivatelCenaObjednavkyState
            )


        },

        ) { innerPadding ->
        zoznamRestauracii(
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize(),
            contentPadding = innerPadding,
            onItemClick = onItemClick,
            zoznamRestauracie = restauracieUiState.restauracieList
        )
    }


}

@Composable
fun zoznamRestauracii(
    modifier: Modifier,
    zoznamRestauracie: List<Restauracia>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onItemClick: () -> Unit = {}
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        items(
            items = zoznamRestauracie,
            key = { restauracia -> restauracia.restauraciaID }) { restauracia ->
            restauraciaRozlozenie(restauracia = restauracia, modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, top = 15.dp)
                .clickable {
                    VybrataRestauracia.nazov = restauracia.nazov
                    onItemClick()
                })

        }


    }
}

@Composable
fun restauraciaRozlozenie(restauracia: Restauracia, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = restauracia.nazov,
                color = Color.Black,
                fontSize = 35.sp,
                textAlign = TextAlign.Center
            )

        }


    }
}



