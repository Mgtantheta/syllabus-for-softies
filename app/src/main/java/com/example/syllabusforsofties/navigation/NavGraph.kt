package com.example.syllabusforsofties.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.syllabusforsofties.detail_screen.DetailScreen
import com.example.syllabusforsofties.detail_screen.DetailScreenViewModel
import com.example.syllabusforsofties.home_screen.HomeScreen
import com.example.syllabusforsofties.home_screen.HomeScreenViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController, viewModel = HomeScreenViewModel())
        }
        composable(
            route = Screen.Detail.route
        ) {
            DetailScreen(navController = navController, viewModel = DetailScreenViewModel())
        }
    }
}
