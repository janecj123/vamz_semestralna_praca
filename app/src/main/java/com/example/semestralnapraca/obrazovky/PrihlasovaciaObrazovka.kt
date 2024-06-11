package com.example.semestralnapraca.obrazovky


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

object PrihlasovanieDestination : NavigationDestination {
    override val route = "prihlasovanie"
}



@Composable
fun PrihlasovaciaObrazovka(
    prihlasovanieViewModel: PrihlasovaciaObrazovkaViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
    onRegistrationClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val prihlasovanieUiState by prihlasovanieViewModel.prihlasovanieUiState.collectAsState()
    val image = painterResource(R.drawable.pozadie_prihlasovacie)

    Box(modifier = Modifier.background(Color.Gray)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = image,
            contentDescription = null
        )



        HlavneOkno(
            prihlasovacieMeno = prihlasovanieViewModel.pouzivatelUiState.pouzivatel,
            prihlasovacieHeslo = prihlasovanieViewModel.pouzivatelUiState.sifrovaneHeslo,
            zadavanieMena = prihlasovanieViewModel::zmenaPrihlasovaciehoMena,
            zadavanieHesla = prihlasovanieViewModel::zmenaPrihlasovaciehoHesla,
            stlacenieTlacidlaPrihlasit = {
                if (prihlasovanieViewModel.overPrihlasenie(prihlasovanieUiState.pouzivateliaList)) {

                    onLoginClick()
                }
            },
            stlacenieTlacidlaRegistracie = { onRegistrationClick() })

    }
}

@Composable
fun HlavneOkno(
    prihlasovacieMeno: String,
    prihlasovacieHeslo: String,
    zadavanieMena: (String) -> Unit,
    zadavanieHesla: (String) -> Unit,
    stlacenieTlacidlaPrihlasit: () -> Unit,
    stlacenieTlacidlaRegistracie: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            modifier = Modifier.padding(0.dp, 20.dp),
            text = stringResource(R.string.nazov_aplikacie),
            fontSize = 45.sp,
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            //lineHeight = 70.sp,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        prihlasovacieRozhranie(

            prihlasovacieMeno,
            prihlasovacieHeslo,
            zadavanieMena,
            zadavanieHesla,
            stlacenieTlacidlaPrihlasit,
            stlacenieTlacidlaRegistracie
        )
    }
}

@Composable
fun prihlasovacieRozhranie(
    prihlasovacieMeno: String,
    prihlasovacieHeslo: String,
    zadavanieMena: (String) -> Unit,
    zadavanieHesla: (String) -> Unit,
    stlacenieTlacidlaPrihlasit: () -> Unit,
    stlacenieTlacidlaRegistracie: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            textStyle = TextStyle(color = Color.White),
            value = prihlasovacieMeno,
            onValueChange = { zadavanieMena(it) },
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.prihlasovacie_meno_label),
                    color = Color.White
                )
            }

        )


        OutlinedTextField(
            modifier = Modifier
                .widthIn(max = 280.dp),
            textStyle = TextStyle(color = Color.White),
            value = prihlasovacieHeslo,
            onValueChange = { zadavanieHesla(it) },
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.heslo_label),
                    color = Color.White
                )
            })


        Row(
            modifier = Modifier
                .padding(30.dp, 20.dp)
                .width(350.dp),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            Button(modifier = Modifier
                .padding(10.dp)
                .size(140.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = { stlacenieTlacidlaRegistracie() }


            ) {
                Text(
                    stringResource(R.string.registracia),
                    color = Color.Black
                )
            }
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .size(140.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = { stlacenieTlacidlaPrihlasit() },

                ) {
                Text(
                    stringResource(R.string.prihlasit),
                    color = Color.Black
                )
            }

        }


    }
}



