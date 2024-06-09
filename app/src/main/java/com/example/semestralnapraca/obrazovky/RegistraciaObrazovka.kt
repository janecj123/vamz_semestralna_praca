package com.example.semestralnapraca.obrazovky

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.AppViewModelProvider
import com.example.semestralnapraca.R
import com.example.semestralnapraca.navigacia.NavigationDestination
import com.example.semestralnapraca.view_modely.PrihlasovaciaObrazovkaViewModel

object RegistraciaDestination : NavigationDestination {
    override val route = "registracia"
}


@Composable
fun oknoRegistracie(prihlasovanieViewModel: PrihlasovaciaObrazovkaViewModel = viewModel(
    factory = AppViewModelProvider.Factory,
),onSaveClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.meno,
            onValueChange = prihlasovanieViewModel::zmenMeno,
            singleLine = true,
                    label = {
                Text(
                    stringResource(R.string.meno_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.priezvisko,
            onValueChange = prihlasovanieViewModel::zmenPriezvisko,
            label = {
                Text(
                    stringResource(R.string.priezvisko_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.pouzivatel,
            onValueChange = prihlasovanieViewModel::zmenaPrihlasovaciehoMena,
            label = {
                Text(
                    stringResource(R.string.pou_vate_sk_meno_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
            .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.sifrovaneHeslo,
            onValueChange = prihlasovanieViewModel::zmenaPrihlasovaciehoHesla,
            label = {
                Text(
                    stringResource(R.string.heslo_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value =  prihlasovanieViewModel.pouzivatelUiState.email,
            onValueChange = prihlasovanieViewModel::zmenEmail,
            label = {
                Text(
                    stringResource(R.string.e_mail_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.adresa,
            onValueChange = prihlasovanieViewModel::zmenAdresu,
            label = {
                Text(
                    stringResource(R.string.adresa_label),
                    color = Color.Black
                )})

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.vek,
            onValueChange = prihlasovanieViewModel::zmenVek,
            label = {
                Text(
                    stringResource(R.string.vek_label),
                    color = Color.Black
                )})



        OutlinedTextField(
            modifier = Modifier
            .widthIn(max = 280.dp),
            value = prihlasovanieViewModel.pouzivatelUiState.cislo,
            onValueChange = prihlasovanieViewModel::zmenCislo,
            label = {
                Text(
                    stringResource(R.string.telefon_cislo_label),
                    color = Color.Black
                )})

        Button( modifier = Modifier
            .padding(10.dp)
            .size(170.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            onClick = {
            if (!prihlasovanieViewModel.overRegistraciu()) {
                prihlasovanieViewModel.ulozPouzivatela()
                onSaveClick()
            }
            }
        ){
            Text(
                stringResource(R.string.tlacidlo_registrovat),
                color = Color.Black
            )
            
        }
    }


}

