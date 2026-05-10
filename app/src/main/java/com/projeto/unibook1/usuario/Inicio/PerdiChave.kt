package com.projeto.unibook1.usuario.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

private val Blue      = Color(0xFF2196F3)
private val LightBlue = Color(0xFFEFF6FF)
private val CardBg    = Color(0xFFFFFFFF)

@Composable
fun PerdiScreen(navController: NavController, onBackClick: () -> Unit) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = CardBg, tonalElevation = 0.dp) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("tela_inicial") },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Início") },
                    label = { Text("Início", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("mapa") },
                    icon = { Icon(Icons.Outlined.Map, contentDescription = "Mapa") },
                    label = { Text("Mapa", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Blue,
                        selectedTextColor = Blue,
                        indicatorColor = LightBlue
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("livros_main") },
                    icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Livros") },
                    label = { Text("Livros", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("perfil") },
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil", fontSize = 11.sp) }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF6F6F9))
                .padding(14.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "⬅️ Perdi minha chave",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackClick() }
            )

            Spacer(modifier = Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "🗝️", style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Fique calmo, Unifriend! \nTudo tem uma solução.",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Não se preocupe. Para recuperar o acesso ao seu armário ou solicitar uma nova chave, por favor, dirija-se à recepção principal da biblioteca munido do seu número de matrícula ou documento com foto.",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "NÚMERO DO ARMÁRIO", fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text(
                        text = "123",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1F7))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📍")
                    Text(text = "Recepção Principal", fontWeight = FontWeight.Bold)
                    Text(text = "Biblioteca Central")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerdiPreview() {
    PerdiScreen(navController = rememberNavController(), onBackClick = {})
}