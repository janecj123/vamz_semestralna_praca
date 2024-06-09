//@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.semestralnapraca.obrazovky

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.AplikaciaTopBar
import com.example.semestralnapraca.AppViewModelProvider
import com.example.semestralnapraca.R
import com.example.semestralnapraca.navigacia.NavigationDestination
import com.example.semestralnapraca.view_modely.ObrazovkaRestauracieViewModel
import com.example.semestralnapraca.view_modely.PrihlasenyPouzivatel
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel
import data.restauracia.Restauracia


object RestauracieDestination : NavigationDestination {
    override val route = "restauracie"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hlavnaObrazovka(modifier: Modifier = Modifier,
                    prihlasovanieViewModel: PrihlasovaciaObrazovkaViewModel = viewModel(
    factory = AppViewModelProvider.Factory),
                   onItemClick: (Restauracia) -> Unit,
                    onAddClick: () -> Unit,
                    obrazovkaRestauracieViewModel: ObrazovkaRestauracieViewModel = viewModel( factory = AppViewModelProvider.Factory)
                    ) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val restauracieUiState by obrazovkaRestauracieViewModel.restauracieUiState.collectAsState()


    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        AplikaciaTopBar(
            pouzivatel = PrihlasenyPouzivatel.meno + " " + PrihlasenyPouzivatel.priezvisko,
            mozeNavigovatDozadu = false,
            scrollBehavior = scrollBehavior
        )
//        Button(onClick = onAddClick) {
//
//        }
    },




    ) { innerPadding ->
        zoznamRestauracii(modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1.5f),
            contentPadding = innerPadding,

            zoznamRestauracie = restauracieUiState.restauracieList)
    }



}

@Composable
fun zoznamRestauracii(modifier: Modifier,
                      zoznamRestauracie: List<Restauracia>,
                      contentPadding: PaddingValues = PaddingValues(0.dp),
                      onItemClick: (Restauracia) -> Unit = {}
                      ) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = zoznamRestauracie, key = { it.restauraciaID }) { item ->
            restauraciaRozlozenie(restauracia = item, modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .clickable { onItemClick(item)})

        }
    }
}

@Composable
fun restauraciaRozlozenie(restauracia : Restauracia, modifier: Modifier = Modifier) {

    Card(modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hlavnaObrazovka2(modifier: Modifier = Modifier,
                    onItemClick: (Restauracia) -> Unit,

) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    //val restauracieUiState by obrazovkaRestauracieViewModel.restauracieUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AplikaciaTopBar(
                pouzivatel = "Fero",
                mozeNavigovatDozadu = false,
                scrollBehavior = scrollBehavior
            )
        },

        ) { innerPadding ->
        zoznamRestauracii2(modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1.5f),
            contentPadding = innerPadding,
            zoznamRestauracie = emptyList())
    }



}

@Composable
fun zoznamRestauracii2(modifier: Modifier,
                      zoznamRestauracie: List<Restauracia>,
                      contentPadding: PaddingValues = PaddingValues(0.dp),
                      onItemClick: (Restauracia) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = zoznamRestauracie, key = { it.restauraciaID }) { item ->
            restauraciaRozlozenie(restauracia = item, modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .clickable { onItemClick(item) })

        }
    }
}


@Preview
@Composable
fun spusti() {
    hlavnaObrazovka2(modifier = Modifier.fillMaxSize(), onItemClick = {})
}