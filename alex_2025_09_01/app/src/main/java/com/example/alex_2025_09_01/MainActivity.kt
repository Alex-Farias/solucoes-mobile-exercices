package com.example.alex_2025_09_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                TreasureHuntApp()
            }
        }
    }
}

@Composable
fun TreasureHuntApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("clue/{clueNumber}") { backStackEntry ->
            val clueNumber = backStackEntry.arguments?.getString("clueNumber")?.toIntOrNull() ?: 1
            ClueScreen(navController = navController, clueNumber = clueNumber)
        }
        composable("treasure") {
            TreasureScreen(navController = navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo à Caça ao Tesouro!", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("clue/1") }) {
            Text("Iniciar caça ao tesouro")
        }
    }
}

@Composable
fun ClueScreen(navController: NavController, clueNumber: Int) {
    val clues = listOf(
        "Pista 1: Sou um animal de estimação que mia.",
        "Pista 2: Sou a cor do céu em um dia claro.",
        "Pista 3: Sou o planeta em que vivemos."
    )
    val currentClue = clues.getOrElse(clueNumber - 1) { "Pista não encontrada." }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = currentClue, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Voltar")
            }
            Button(onClick = {
                if (clueNumber < clues.size) {
                    navController.navigate("clue/${clueNumber + 1}")
                } else {
                    navController.navigate("treasure")
                }
            }) {
                Text("Próxima Pista")
            }
        }
    }
}

@Composable
fun TreasureScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Parabéns! Você encontrou o tesouro!", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Recomeçar a caça")
        }
    }
}