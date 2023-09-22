package com.example.syllabusforsofties.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.syllabusforsofties.detail_screen.DetailScreen
import com.example.syllabusforsofties.home_screen.HomeScreen
import com.example.syllabusforsofties.home_screen.HomeScreenViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable(
            route = "home_screen"
        ) {
            HomeScreen(navController = navController,
                viewModel = HomeScreenViewModel(),
                onNavigateToDetailScreen = { courseNamePram ->
                navController.navigate("detail_screen/$courseNamePram")
            })
        }
        composable(
            route = "detail_screen/{courseName}",
            arguments = listOf(
                navArgument("courseName") {
                    type = NavType.StringType
                })
        ) {
            val courseNamePram = it.arguments?.getString("courseNamePram") ?: ""
            DetailScreen(navController = navController, courseNamePram = courseNamePram)
        }
    }
}
