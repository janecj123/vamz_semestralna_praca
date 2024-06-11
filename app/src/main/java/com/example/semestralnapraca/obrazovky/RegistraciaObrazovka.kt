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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel
import kotlinx.coroutines.launch

object RegistraciaDestination : NavigationDestination {
    override val route = "registracia"
}


@Composable
fun oknoRegistracie(
    prihlasovanieViewModel: PrihlasovaciaObrazovkaViewModel = viewModel(
        factory = AppViewModelProvider.Factory,
    ), onSaveClick: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val prihlasovanieUiState by prihlasovanieViewModel.prihlasovanieUiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF666666))
            .padding(bottom = 36.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.registracia_pouzivatela),
            modifier = Modifier.padding(top = 37.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )


        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .padding(top = 10.dp),

            value = prihlasovanieViewModel.pouzivatelUiState.meno,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenMeno,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.meno_label),
                    color = Color.White
                )
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.priezvisko,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenPriezvisko,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.priezvisko_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.pouzivatel,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenaPrihlasovaciehoMena,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.pou_vate_sk_meno_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.sifrovaneHeslo,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenaPrihlasovaciehoHesla,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.heslo_label),
                    color = Color.White
                )
            })

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.email,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenEmail,
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
            value = prihlasovanieViewModel.pouzivatelUiState.adresa,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenAdresu,
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
            value = prihlasovanieViewModel.pouzivatelUiState.vek,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenVek,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.vek_label),
                    color = Color.White
                )
            })



        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.cislo,
            textStyle = TextStyle(color = Color.White),
            onValueChange = prihlasovanieViewModel::zmenCislo,
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.telefon_cislo_label),
                    color = Color.White
                )
            })

        Button(modifier = Modifier
            .padding(bottom = 30.dp)
            .size(170.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                if (!prihlasovanieViewModel.overRegistraciu(prihlasovanieUiState.pouzivateliaList)) {
                    prihlasovanieViewModel.ulozPouzivatela()
                    onSaveClick()
                } else {

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "ZlE ZADANÉ ÚDAJE",
                            actionLabel = "OK",
                        )
                    }
                }
            })
        {
            Text(
                stringResource(R.string.tlacidlo_registrovat),
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

