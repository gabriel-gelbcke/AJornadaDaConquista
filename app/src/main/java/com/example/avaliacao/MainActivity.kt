package com.example.avaliacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avaliacao.ui.theme.AvaliacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvaliacaoTheme {
                val backgroundImage2: Painter = painterResource(id = R.drawable.inicio)
                var clickesNecessarios by remember { mutableDoubleStateOf(50.0) }
                var clickesDados by remember { mutableDoubleStateOf(0.0) }
                var message by remember { mutableStateOf("Começo da Jornada") }
                var desistir by remember { mutableDoubleStateOf(0.0) }

                val backgroundImage: Painter = when {
                    clickesDados <= (clickesNecessarios * 0.33) -> painterResource(id = R.drawable.inicio)
                    clickesDados > (clickesNecessarios * 0.33) && clickesDados <= (clickesNecessarios * 0.66) -> painterResource(id = R.drawable.mediana)
                    clickesDados > (clickesNecessarios * 0.66) && clickesDados <= (clickesNecessarios * 0.99) -> painterResource(id = R.drawable.finals)
                    else -> painterResource(id = R.drawable.conquista)
                }

                if(desistir >= 1){
                    message = "Desistiu da Jornada"
                }else if(clickesDados <= (clickesNecessarios*0.33)){
                    message = "Começo da Jornada"
                }else if(clickesDados > (clickesNecessarios*0.33) && clickesDados <= (clickesNecessarios*0.66)){
                    message = "Meio da Jornada"
                }else if(clickesDados > (clickesNecessarios*0.66) && clickesDados <= (clickesNecessarios*0.99)){
                    message = "Quase no Final"
                }else if(clickesDados > (clickesNecessarios*0.99)){
                    message = "Chegou no Final"
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(
                            painter = backgroundImage,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        //Texto Jornada
                        Box(modifier = Modifier.size(640.dp), contentAlignment = Alignment.TopCenter){
                            Text(
                                text = "$message",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                            )
                        }

                        //Texto Necessários
                        Box(modifier = Modifier.size(width = 330.dp, height = 540.dp)){
                            Text(
                                text = "Faça $clickesNecessarios cliques para avançar",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                            )
                        }

                        //Texto Clickes
                        Box(modifier = Modifier.size(460.dp), contentAlignment = Alignment.Center){
                            Text(
                                text = "Seus Cliques: $clickesDados",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                            )
                        }

                        //Botão Cliques
                        Box(modifier = Modifier.size(170.dp), contentAlignment = Alignment.Center){
                        Button(
                            onClick = {
                                clickesDados++
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red, // Cor de fundo do botão
                                contentColor = Color.White // Cor do texto do botão
                            ),
                            modifier = Modifier.size(
                                width = 200.dp, height = 60.dp
                            )
                        ) {
                            Text(text = "Desistir",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,)
                            }
                        }
                        //Botão Desistir
                        Box(modifier = Modifier.size(width = 250.dp, height = 330.dp), contentAlignment = Alignment.Center){
                            Button(
                                onClick = {
                                    clickesDados++
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Black, // Cor de fundo do botão
                                    contentColor = Color.White // Cor do texto do botão
                                ),
                                modifier = Modifier.size(
                                    width = 250.dp, height = 70.dp
                                )
                            ) {
                                Text(text = "Click +1",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,)
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = ""
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AvaliacaoTheme {
        Greeting("Android")
        }
    }
}