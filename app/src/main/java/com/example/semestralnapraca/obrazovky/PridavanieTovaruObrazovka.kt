package com.example.semestralnapraca.obrazovky

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.AppViewModelProvider
import com.example.semestralnapraca.R
import com.example.semestralnapraca.navigacia.NavigationDestination
import com.example.semestralnapraca.view_modely.PridavanieTovaruViewModel
import com.example.semestralnapraca.view_modely.VybrataRestauracia
import kotlinx.coroutines.launch

object PridavanieTovaruDestination : NavigationDestination {
    override val route = "pridavanieTovaru"
}

@Composable
fun pridavanieTovaruObrazovka(
    onSaveClick: () -> Unit,
      pridavanieTovaruViewModel: PridavanieTovaruViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF666666))
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.pridavanie_tovaru),
            modifier = Modifier.padding(top = 37.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White)

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieTovaruViewModel.pridavanyTovarUiState.nazov,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieTovaruViewModel::zmenaNazvu,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.nazov_tovaru_label),
                    color = Color.White
                )
            })


        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieTovaruViewModel.pridavanyTovarUiState.cena,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieTovaruViewModel::zmenaCeny,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.cena_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieTovaruViewModel.pridavanyTovarUiState.vaha,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieTovaruViewModel::zmenaVahy,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.vaha_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieTovaruViewModel.pridavanyTovarUiState.popis,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieTovaruViewModel::znemaPopisu,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.popis_label),
                    color = Color.White
                )
            })
        Button(modifier = Modifier.size(140.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                if (pridavanieTovaruViewModel.pridajTovar(VybrataRestauracia.nazov)) {
                    onSaveClick()
                }else {

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "ZlE ZADANÉ ÚDAJE",
                            actionLabel = "OK",)
                    }
                }
            }) {
            Text(
                stringResource(R.string.pridat),
                color = Color.Black
            )
        }
        SnackbarHost(hostState = snackbarHostState,
            snackbar = { snackbarData ->
                Snackbar(
                    snackbarData,
                    contentColor = Color.Red,
                    actionColor = Color.White
                )
            })


    }
}