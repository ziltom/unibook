package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.projeto.unibook1.admin.DefinirSenhaScreen
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.admin.AdminRegisterScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme



import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario


import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen
import com.projeto.unibook1.admin.RecuperarSenhaScreen
import com.projeto.unibook1.usuario.cadastro.CadastroScreen
import com.projeto.unibook1.usuario.cadastro.DefinirNovaSenhaScreen
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen
import com.projeto.unibook1.usuario.cadastro.RecuperarSenhaScreen as RecuperarSenhaAlunoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            Unibook1Theme {


                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = "definir_senha_admin") {

                    // Tela de Login
                    composable("login_admin") {
                        AdminLoginScreen(
                            onNavigateToRegister = {
                                navController.navigate("admin_register")
                            },
                            onNavigateToForgotPassword = {
                                navController.navigate("forgot_password")
                            },
                            onLoginSuccess = {
                                navController.navigate("admin_home") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Tela de Cadastro
                    composable("admin_register") {
                        AdminRegisterScreen(
                            onBackToLogin = {

                                navController.navigate("login_admin") {

                                    popUpTo("login_admin") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Tela de Definir Senha Admin
                    composable("definir_senha_admin") {
                        DefinirSenhaScreen(
                            onNavigateToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                navController.navigate("admin_register")
                            }
                        )
                    }

                    // Tela de Esqueci a Senha
                    composable("forgot_password") {
                        RecuperarSenhaScreen(
                            onNavigateToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                navController.navigate("admin_register") {
                                    popUpTo("login_admin")
                                }
                            }
                        )
                    }

                    // Tela do Mapa Renan
                    composable("mapa") {
                        MapScreen(
                            onReservaClick = {
                                navController.navigate("reserva")
                            }
                        )
                    }

                    // Tela de Reserva, essa é geral, de todo mundo
                    composable("reserva") {
                        TelaReservaArmario()
                    }



                    // Tela Principal do Admin (Home)
                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = {

                                navController.navigate("admin_profile")
                            },
                            onOpenScannerClick = {
                                // Lógica futura do seu scanner
                            },
                            onStudentClick = { _ ->
                                // Lógica futura ao clicar em um aluno na lista
                            }
                        )
                    }

                    // Tela de Perfil do Admin
                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = {

                                navController.popBackStack()
                            },
                            onChangeProfilePictureClick = {
                                // Lógica futura para abrir a galeria e mudar a foto
                            }
                        )
                    }

                    // --- TELAS DE USUÁRIO (ALUNO) ---

                    // Tela de Login do Aluno
                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha_aluno") },
                            onLoginSucesso = {
                                navController.navigate("mapa") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Tela de Cadastro do Aluno
                    composable(route = "cadastro") {
                        CadastroScreen(
                            onNavigateToLogin = { navController.navigate(route = "login_aluno") },
                            onNavigateToSuporte = { }
                        )
                    }

                    // Tela de Recuperar Senha do Aluno
                    composable(route = "recuperar_senha_aluno") {
                        RecuperarSenhaAlunoScreen(
                            onVoltarLogin = { navController.navigate(route = "login_aluno") },
                            onContinuar = { navController.navigate("definir_nova_senha_aluno") }
                        )
                    }

                    // Tela de Definir Nova Senha do Aluno
                    composable("definir_nova_senha_aluno") {
                        DefinirNovaSenhaScreen(
                            onVoltarLogin = { navController.navigate("login_aluno") },
                            onSenhaAtualizada = {
                                navController.navigate("login_aluno") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }

                }
            }
        }
    }
}
