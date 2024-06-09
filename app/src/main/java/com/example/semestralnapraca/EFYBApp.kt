package com.example.semestralnapraca

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.semestralnapraca.navigacia.AplikaciaNavigacia

@Composable
fun EFYBApp(navController: NavHostController = rememberNavController()) {
    AplikaciaNavigacia(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplikaciaTopBar(
    pouzivatel: String,
    mozeNavigovatDozadu: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigujDozadu: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(pouzivatel) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (mozeNavigovatDozadu) {
                IconButton(onClick = navigujDozadu) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.tlacidlo_spat_top_bar)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(R.color.brown),
            titleContentColor = Color.White
        ),

    )
}
