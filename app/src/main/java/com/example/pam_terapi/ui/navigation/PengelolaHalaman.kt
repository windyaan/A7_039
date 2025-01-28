package com.example.pam_terapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam_terapi.ui.view.mahasiswa.DestinasiDetail
import com.example.pam_terapi.ui.view.mahasiswa.DestinasiEntry
import com.example.pam_terapi.ui.view.mahasiswa.DestinasiHome
import com.example.pam_terapi.ui.view.mahasiswa.DestinasiUpdate
import com.example.pam_terapi.ui.view.mahasiswa.DetailView
import com.example.pam_terapi.ui.view.mahasiswa.EntryPsScreen
import com.example.pam_terapi.ui.view.mahasiswa.HomeScreen
import com.example.pam_terapi.ui.view.mahasiswa.UpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable (DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { idPasien ->
                    //
                    navController.navigate("${DestinasiDetail.route}/$idPasien")
                }
            )
        }
        composable (DestinasiEntry.route){
            EntryPsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){inclusive = true}
                }
            })
        }
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.ID){
                    type = NavType.StringType
                }
            )
        ) {
            val idPasien = it.arguments?.getString(DestinasiDetail.ID)
            idPasien?.let {
                DetailView(
                    NavigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {navController.navigate("${DestinasiUpdate.route}/$it")},
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateView(
                NavigateBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
            )
        }
    }
}