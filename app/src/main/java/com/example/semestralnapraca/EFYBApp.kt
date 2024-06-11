package com.example.semestralnapraca


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca.navigacia.AplikaciaNavigacia


@Composable
fun EFYBApp(navController: NavHostController = rememberNavController()) {
    AplikaciaNavigacia(navController = navController)
}


// App top bar ktory sa pouziva na nejktorych obrazovkach pomocou scafoldu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplikaciaTopBar(
    pouzivatel: String,
    mozePridavat: Boolean,
    cenaObjednavky: Double,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigujDoPridavania: () -> Unit = {}
) {


    CenterAlignedTopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    pouzivatel, color = Color.White,
                    textAlign = TextAlign.Center
                )



                Text(
                    cenaObjednavky.toString() + "€",
                    modifier = Modifier,

                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }


        },

        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (mozePridavat) {
                IconButton(onClick = navigujDoPridavania, modifier.padding(top = 5.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.tlacidlopridaj)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(R.color.silver),
            titleContentColor = Color.White
        ),


        )
}
