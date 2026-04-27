package com.projeto.unibook1.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CorPrimaria = Color(0xFF6A1B9A)
val CorDeFundo = Color(0xFFF8F9FF)

data class SolicitacaoEmprestimo(val nome: String, val matricula: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEmprestimos(
    onStudentClick: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToEmprestimos: () -> Unit,
    onNavigateToLivros: () -> Unit
) {
    val listaDeSolicitacoes = listOf(
        SolicitacaoEmprestimo("Ana Clara Silva", "20240129"),
        SolicitacaoEmprestimo("Lucas Ferreira", "20240842"),
        SolicitacaoEmprestimo("Mariana Costa", "20241105")
    )

    Scaffold(
        containerColor = CorDeFundo,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = CorPrimaria)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "BIBLIOTECA CENTRAL",
                            style = MaterialTheme.typography.titleMedium,
                            color = CorPrimaria,
                            fontWeight = FontWeight.Black
                        )
                    }
                },
                actions = {

                }
            )
        },
        bottomBar = {
            // Usando a Navbar Unificada do Passo 1!
            AdminBottomNavBar(
                currentRoute = "admin_emprestimos",
                onNavigateToHome = onNavigateToHome,
                onNavigateToEmprestimos = onNavigateToEmprestimos,
                onNavigateToLivros = onNavigateToLivros
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 20.dp)
        ) {
            OutlinedTextField(
                value = "", onValueChange = {}, placeholder = { Text("Buscar por nome do aluno...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, focusedBorderColor = CorPrimaria, unfocusedBorderColor = Color.Transparent)
            )

            Text("SOLICITAÇÕES DE EMPRÉSTIMO", fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 12.dp))

            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(listaDeSolicitacoes) { solicitacao ->
                    CardEstudante(solicitacao = solicitacao, onClick = { onStudentClick(solicitacao.matricula) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEstudante(solicitacao: SolicitacaoEmprestimo, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
        color = Color.White, shadowElevation = 2.dp, onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(50.dp), shape = CircleShape, color = Color.LightGray) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = solicitacao.nome, fontWeight = FontWeight.Bold, color = CorPrimaria)
                Text(text = solicitacao.matricula, color = Color.Gray, fontSize = 12.sp)
            }
            IconButton(onClick = {}) { Icon(Icons.Default.Search, contentDescription = null, tint = CorPrimaria) }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminEmprestimosPreview() {
    MaterialTheme {
        AdminEmprestimos(
            onStudentClick = {},
            onNavigateToHome = {},
            onNavigateToEmprestimos = {},
            onNavigateToLivros = {}
        )
    }
}



