package com.example.semestralnapraca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.semestralnapraca.obrazovky.PrihlasovaciaObrazovka

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            PrihlasovaciaObrazovka()
        }
    }
}
//fun main(){
 //   val t1 = Tovar("Pizza",8.35)
  //  val t2 = Tovar("Rezne",7.22)
 //   val p = Podnik("Mania","Zilina")
 //   val z = Zakaznik("Jakub","Janec",26,"0948067869",
  //      "Kysucke Nove Mesto","aaa")
  //  z.vytvorObjednavku(p)
 //   z.pridajDoObjednavky(t1)
  //  z.pridajDoObjednavky(t2)
 //   z.vypisObjednavku(0)



