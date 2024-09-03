package com.example.avaliacao

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import kotlin.random.Random
import kotlin.text.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvaliacaoTheme {
                var clickesNecessarios by remember { mutableDoubleStateOf(0.0) }
                var clickesDados by remember { mutableDoubleStateOf(0.0) }
                var message by remember { mutableStateOf("Começo da Jornada") }

                var Desistir by remember { mutableStateOf(false) }
                var Venceu by remember { mutableStateOf(false) }
                var Inicio by remember { mutableStateOf(true) }

                val desistir by remember { mutableDoubleStateOf(0.0) }

                var messageRandom by remember { mutableStateOf("") }
                var messageDados by remember { mutableStateOf("") }

                val painter2 = painterResource(id = R.drawable.conquista)
                var backgroundImage2 by remember { mutableStateOf(painter2) }

                val painter3 = painterResource(id = R.drawable.desistencia)
                var backgroundImage3 by remember { mutableStateOf(painter3) }

                if(Inicio){
                    val numeroAleatorio = Random.nextInt(1, 50)
                    clickesNecessarios = numeroAleatorio.toDouble()
                    messageRandom = String.format("%.0f", clickesNecessarios)
                    messageDados = String.format("%.0f", clickesDados)
                    clickesDados = 0.0;
                    Inicio = false;
                }

                messageDados = String.format("%.0f", clickesDados)

                val backgroundImage: Painter = when {
                    clickesDados <= (clickesNecessarios * 0.33) -> painterResource(id = R.drawable.inicio)
                    clickesDados > (clickesNecessarios * 0.33) && clickesDados <= (clickesNecessarios * 0.66) -> painterResource(id = R.drawable.mediana)
                    clickesDados > (clickesNecessarios * 0.66) && clickesDados <= (clickesNecessarios * 0.99) -> painterResource(id = R.drawable.finals)
                    else -> painterResource(id = R.drawable.conquista)
                }

                if(desistir >= 1){
                    message = "Desistiu da Jornada\n\nDeseja Recomeçar?"
                }else if(clickesDados <= (clickesNecessarios*0.33)){
                    message = "Começo da Jornada"
                }else if(clickesDados > (clickesNecessarios*0.33) && clickesDados <= (clickesNecessarios*0.66)){
                    message = "Meio da Jornada"
                }else if(clickesDados > (clickesNecessarios*0.66) && clickesDados <= (clickesNecessarios*0.99)){
                    message = "Quase no Final"
                }else if(clickesDados > (clickesNecessarios*0.99)){
                    message = "Você Chegou no Final\n\nParabéns!!!\n\nDeseja Recomeçar?"
                    Venceu = true;
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    if(!Desistir) {
                        if (!Venceu){
                            //TELA SE O CARA NÃO DESISTIR E NÃO TIVER VENCIDO AINDA
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Image(
                                    painter = backgroundImage,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                //Texto Jornada
                                Box(
                                    modifier = Modifier.size(640.dp),
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    Text(
                                        text = "$message",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                                //Texto Necessários
                                Box(modifier = Modifier.size(width = 330.dp, height = 540.dp)) {
                                    Text(
                                        text = "Clique $messageRandom vezes para vencer",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }


                                //Texto Clickes
                                Box(
                                    modifier = Modifier.size(460.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Seus Cliques: $messageDados",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp,
                                    )
                                }

                                //Botão Desistir
                                Box(
                                    modifier = Modifier.size(170.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = {
                                            Desistir = true;
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Red,
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.size(
                                            width = 200.dp, height = 60.dp
                                        )
                                    ) {
                                        Text(
                                            text = "Desistir",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 25.sp,
                                        )
                                    }
                                }
                                //Botão Cliques
                                Box(
                                    modifier = Modifier.size(width = 250.dp, height = 330.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = {
                                            if (clickesDados < clickesNecessarios) {
                                                clickesDados++
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.size(
                                            width = 250.dp, height = 70.dp
                                        )
                                    ) {
                                        Text(
                                            text = "Click +1",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 25.sp,
                                        )
                                    }
                                }
                            }
                        }else {
                            //TELA SE O CARA TIVER VENCIDO
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Image(
                                    painter = backgroundImage2,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                //Texto Jornada
                                Box(
                                    modifier = Modifier.size(560.dp),
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(height = 150.dp, width = 460.dp)
                                            .background(
                                                color = Color.Gray.copy(alpha = 0.5f),
                                            ),
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        Text(
                                            text = "$message",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 35.sp,
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                }

                                //Texto Necessários
                                Box(modifier = Modifier.size(width = 330.dp, height = 450.dp)) {
                                    Text(
                                        text = "",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                                //Texto Clickes
                                Box(
                                    modifier = Modifier.size(460.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp,
                                    )
                                }

                                //Botão Cliques
                                Box(
                                    modifier = Modifier.size(width = 250.dp, height = 620.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = {
                                            Desistir = false;
                                            Venceu = false;
                                            Inicio = true;
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Green,
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.size(
                                            width = 250.dp, height = 70.dp
                                        )
                                    ) {
                                        Text(
                                            text = "Sim",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 25.sp,
                                        )
                                    }
                                }

                                //Botão Cliques
                                Box(
                                    modifier = Modifier.size(width = 250.dp, height = 430.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(
                                        onClick = {
                                            finish()
                                            val intent = Intent(Intent.ACTION_MAIN)
                                            intent.addCategory(Intent.CATEGORY_HOME)
                                            startActivity(intent)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Red,
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.size(
                                            width = 250.dp, height = 70.dp
                                        )
                                    ) {
                                        Text(
                                            text = "Não",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 25.sp,
                                        )
                                    }
                                }
                            }
                        }

                    }else{
                        //TELA SE O CARA DESISTIR
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(
                                painter = backgroundImage3,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            //Texto Jornada
                            Box(
                                modifier = Modifier.size(450.dp),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                Text(
                                    text = "Desistiu da Jornada\n\nDeseja Recomeçar?",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 35.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }

                            //Texto Necessários
                            Box(modifier = Modifier.size(width = 330.dp, height = 450.dp)) {
                                Text(
                                    text = "",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }

                            //Texto Clickes
                            Box(
                                modifier = Modifier.size(460.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                )
                            }

                            //Botão Cliques
                            Box(
                                modifier = Modifier.size(width = 250.dp, height = 620.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = {
                                        Desistir = false;
                                        Venceu = false;
                                        Inicio = true;
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Green,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier.size(
                                        width = 250.dp, height = 70.dp
                                    )
                                ) {
                                    Text(
                                        text = "Sim",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                    )
                                }
                            }

                            //Botão Cliques
                            Box(
                                modifier = Modifier.size(width = 250.dp, height = 430.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = {
                                        finish()
                                        val intent = Intent(Intent.ACTION_MAIN)
                                        intent.addCategory(Intent.CATEGORY_HOME)
                                        startActivity(intent)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier.size(
                                        width = 250.dp, height = 70.dp
                                    )
                                ) {
                                    Text(
                                        text = "Não",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                    )
                                }
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