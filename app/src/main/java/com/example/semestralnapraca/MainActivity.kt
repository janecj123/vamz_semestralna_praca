package com.example.semestralnapraca

import allKindOfGoods.Tovar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.semestralnapraca.ui.theme.SemestralnaPracaTheme

fun main(){
    var t1 = Tovar("Pizza",8.35)
    var t2 = Tovar("Rezne",7.22)
    var p = Podnik("Mania","Zilina")
    p.pridajTovar(t1)
    p.pridajTovar(t2)
    p.vypisPonuku()

}

