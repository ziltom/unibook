package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.ui.theme.Unibook1Theme

// ===== TELAS ADMIN ANTIGAS =====
import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen
import com.projeto.unibook1.admin.RecuperarSenhaScreen

// ===== NOVO FLUXO EMPRÉSTIMOS =====
import com.projeto.unibook1.admin.*

import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "admin_home"
                ) {

                    // =======================
                    // LOGIN / RECUPERAR SENHA
                    // =======================

                    composable("forgot_password") {
                        RecuperarSenhaScreen(
                            onNavigateToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {}
                        )
                    }

                    // ========= MAPA =========
                    composable("mapa") {
                        MapScreen(
                            onReservaClick = {
                                navController.navigate("reserva")
                            }
                        )
                    }

                    // ======= RESERVA ========
                    composable("reserva") {
                        TelaReservaArmario()
                    }

                    // =======================
                    // ADMIN HOME (ANTIGO)
                    // =======================
                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = {
                                navController.navigate("admin_profile")
                            },
                            onOpenScannerClick = {
                                // futuro scanner
                            },
                            onStudentClick = { _ ->
                                // clique aluno
                            },
                            onEmprestimosClick = {
                                navController.navigate("admin_emprestimos")
                            }
                        )
                    }

                    // =======================
                    // PERFIL ADMIN
                    // =======================
                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onChangeProfilePictureClick = {}
                        )
                    }

                    // ======================================
                    // FLUXO EMPRÉSTIMOS (NOVO SISTEMA)
                    // ======================================

                    composable("admin_emprestimos") {
                        AdminGestaoEmprestimos(
                            onNavigateToSolicitacoes = {
                                navController.navigate("admin_solicitacoes")
                            },
                            onNavigateToRegulares = {
                                navController.navigate("admin_regulares")
                            },
                            onNavigateToIrregulares = {
                                navController.navigate("admin_atrasados")
                            },
                            onNavigateToBloqueados = {
                                navController.navigate("admin_bloqueados")
                            }
                        )
                    }

                    // ===== BLOQUEADOS =====
                    composable("admin_bloqueados") {
                        AdminAlunosBloqueados(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = {
                                navController.navigate("admin_perfil_bloqueado")
                            }
                        )
                    }

                    composable("admin_perfil_bloqueado") {
                        AdminPerfilBloqueado(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ===== ATRASADOS =====
                    composable("admin_atrasados") {
                        AdminEmprestimosAtrasados(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = {
                                navController.navigate("admin_detalhes_atraso")
                            }
                        )
                    }

                    composable("admin_detalhes_atraso") {
                        AdminDetalhesAtraso(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ===== REGULARES =====
                    composable("admin_regulares") {
                        AdminEmprestimosRegulares(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = {
                                navController.navigate("admin_perfil_emprestimo")
                            }
                        )
                    }

                    composable("admin_perfil_emprestimo") {
                        AdminPerfilEmprestimo(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // ===== SOLICITAÇÕES =====
                    composable("admin_solicitacoes") {
                        AdminEmprestimos(
                            BackClick = { navController.popBackStack() },
                            onStudentClick = {
                                navController.navigate("admin_perfil_solicitacao")
                            }
                        )
                    }

                    composable("admin_perfil_solicitacao") {
                        AdminPerfilSolicitacao(
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
