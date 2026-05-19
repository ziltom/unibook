package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LoginAlunoScreen(
    onNavigateToCadastro: () -> Unit,
    onNavigateToSuporte: () -> Unit,
    onEsqueceuSenha: () -> Unit,
    onLoginSucesso: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var matricula by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }
    var carregando by remember { mutableStateOf(false) }

    fun realizarLogin() {
        if (matricula.isBlank() || senha.isBlank()) {
            mensagemErro = "Preencha todos os campos"
            return
        }

        carregando = true
        mensagemErro = ""

        // Busca o email associado à matrícula no Firestore
        db.collection("usuarios")
            .whereEqualTo("matricula", matricula)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val email = documents.documents[0].getString("email")
                    if (email != null) {
                        auth.signInWithEmailAndPassword(email, senha)
                            .addOnSuccessListener {
                                carregando = false
                                onLoginSucesso()
                            }
                            .addOnFailureListener { e ->
                                carregando = false
                                mensagemErro = "Senha incorreta ou erro no login"
                            }
                    } else {
                        carregando = false
                        mensagemErro = "Erro ao recuperar dados do usuário"
                    }
                } else {
                    carregando = false
                    mensagemErro = "Matrícula não encontrada"
                }
            }
            .addOnFailureListener { e ->
                carregando = false
                mensagemErro = "Erro de conexão: ${e.localizedMessage}"
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Ícone de formatura
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFFE3F0FF),
                    modifier = Modifier.size(72.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🎓", fontSize = 32.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Login do Aluno",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Campo Matrícula
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Número da Matrícula", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = matricula,
                        onValueChange = { matricula = it },
                        placeholder = { Text("ex: 2683492", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !carregando,
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Senha
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Senha", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        placeholder = { Text("••••••••", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !carregando,
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                                Icon(
                                    imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                                    tint = Color.Gray
                                )
                            }
                        }
                    )
                }

                // Esqueceu a senha (alinhado à direita)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onEsqueceuSenha, enabled = !carregando) {
                        Text("Esqueceu a senha?", color = Color(0xFF2196F3), fontSize = 13.sp)
                    }
                }

                // Mensagem de erro
                if (mensagemErro.isNotEmpty()) {
                    Text(
                        mensagemErro,
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão Entrar
                Button(
                    onClick = { realizarLogin() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = !carregando,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    if (carregando) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Entrar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Não possuo conta → vai para CadastroScreen
                TextButton(onClick = onNavigateToCadastro) {
                    Text("Não possuo conta", color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(    //Colocar o nome ao lado
                    horizontalArrangement = Arrangement.Center, //centraliza na tela
                    verticalAlignment = Alignment.CenterVertically //fica na mesma altura
                ) {
                    Text("Precisa de ajuda?", color = Color.Gray, fontSize = 13.sp)
                    TextButton(onClick = onNavigateToSuporte) {
                        Text(" Contatar Suporte", color = Color(0xFF2196F3), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginAlunoScreenPreview() {
    LoginAlunoScreen(
        onNavigateToCadastro = {},
        onNavigateToSuporte = {},
        onEsqueceuSenha = {},
        onLoginSucesso = {}
    )
}