package com.example.semestralnapraca.obrazovky

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.semestralnapraca.R

@Preview(showBackground = true)
@Composable
fun PrihlasovaciaObrazovka(aplikaciaViewModel: PrihlasovaciaObrazovkaViewModel = viewModel()) {
    val stavRozhrania by aplikaciaViewModel.aktualnyStav.collectAsState()
    val image = painterResource(R.drawable.pozadie_prihlasovacie)

  Box() {
      Image(
          modifier = Modifier.fillMaxSize(),
          painter = image,
          contentDescription = null
      )



            PrihlasovacieOkno(
                prihlasovacieMeno = stavRozhrania.pouzivatel,
                prihlasovacieHeslo = stavRozhrania.heslo,
                zadavanieMeno = { aplikaciaViewModel.zmenaPrihlasovaciehoMeno(it) },
                zadavanieHesla = { aplikaciaViewModel.zmenaPrihlasovaciehoHeslo(it) },
                stlacenieTlacidlaPrihlasit = { aplikaciaViewModel.overPrihlasenie() })

    }
}

@Composable
fun PrihlasovacieOkno( prihlasovacieMeno : String,
                       prihlasovacieHeslo: String,
                       zadavanieMeno: (String) -> Unit,
                       zadavanieHesla: (String) -> Unit,
                       stlacenieTlacidlaPrihlasit: () -> Unit,
                       modifier: Modifier = Modifier) {
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
        Column() {

            OutlinedTextField(
                value = prihlasovacieMeno,
                onValueChange = zadavanieMeno,
                singleLine = true,
                label = {
                    Text(
                        stringResource(R.string.prihlasovacie_meno_label),
                        color = Color.White
                    )
                })


            OutlinedTextField(
                value = prihlasovacieHeslo,
                onValueChange = zadavanieHesla,
                singleLine = true,
                label = {
                    Text(
                        stringResource(R.string.heslo_label),
                        color = Color.White
                    )
                })
            //vytvor row kde budu dva buttony vedla seba
            Row(
                modifier = Modifier.padding(0.dp, 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { stlacenieTlacidlaPrihlasit() },
                    modifier = Modifier.padding(0.dp, 20.dp)
                ) {
                    // Text(stringResource(R.string.prihlasit))
                }
                Button(
                    onClick = { },
                    modifier = Modifier.padding(0.dp, 20.dp)
                ) {
                    // Text(stringResource(R.string.registracia))
                }

            }


        }
    }
}