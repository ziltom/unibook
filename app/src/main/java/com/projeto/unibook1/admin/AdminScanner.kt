package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScannerScreen(
    onBackClick: () -> Unit,
    onScanSuccess: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(5000L)
        onScanSuccess()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Câmera do Scanner", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color(0xFF7B2CBF))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF2C2C2C)), // Fundo escuro simulando a câmera
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
                    .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Alinhe o código QR dentro do quadro",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }


            Box(
                modifier = Modifier
                    .size(250.dp)
                    .border(4.dp, Color(0xFF7B2CBF), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewScanner() {
    AdminScannerScreen({}, {})
}