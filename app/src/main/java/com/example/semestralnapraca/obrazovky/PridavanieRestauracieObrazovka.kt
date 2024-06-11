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
import com.example.semestralnapraca.view_modely.PridavanieRestauraciiViewModel
import kotlinx.coroutines.launch

object PridavanieRestauracieDestination : NavigationDestination {
    override val route = "pridavanieRestauracie"
}


@Composable
fun pridavanieRestauraciiObrazovka(pridavanieRestauraciiViewModel: PridavanieRestauraciiViewModel
                                   = viewModel(factory = AppViewModelProvider.Factory),
                                   onAddClick: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF666666))
            .padding( bottom = 36.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.pridavanie_podniku),
            modifier = Modifier.padding(top = 37.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White)

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieRestauraciiViewModel.pridavanaRestauraciaUiState.nazov,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieRestauraciiViewModel::zmenaNazvu,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.nazov_podniku_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieRestauraciiViewModel.pridavanaRestauraciaUiState.adresa,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieRestauraciiViewModel::zmenaAdresy,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.adresa_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieRestauraciiViewModel.pridavanaRestauraciaUiState.email,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieRestauraciiViewModel::zmenaEmailu,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.e_mail_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieRestauraciiViewModel.pridavanaRestauraciaUiState.cislo,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieRestauraciiViewModel::zmenaCisla,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.telefon_cislo_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = pridavanieRestauraciiViewModel.pridavanaRestauraciaUiState.webovaStranka,
            textStyle = TextStyle(color = Color.White),
            onValueChange = pridavanieRestauraciiViewModel::zmenaWebovejStranky,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.webova_stranka_label),
                    color = Color.White
                )
            })

       Button(modifier = Modifier.size(140.dp, 50.dp),
           colors = ButtonDefaults.buttonColors(Color.LightGray),
        onClick = { if (pridavanieRestauraciiViewModel.pridajRestauraciu()){
           onAddClick()
       } else {

            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "ZlE ZADANÉ ÚDAJE",
                    actionLabel = "OK",)
            }
        } }) {
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