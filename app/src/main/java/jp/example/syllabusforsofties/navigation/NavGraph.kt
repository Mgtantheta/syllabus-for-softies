package jp.example.syllabusforsofties.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.example.syllabusforsofties.detail_screen.DetailScreen
import jp.example.syllabusforsofties.detail_screen.DetailScreenViewModel
import jp.example.syllabusforsofties.home_screen.HomeScreen
import jp.example.syllabusforsofties.home_screen.HomeScreenViewModel


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
                viewModel = HomeScreenViewModel()
            )
        }
        composable(
            route = "detail_screen/{courseName}",
            arguments = listOf(
                navArgument("courseName") {
                    type = NavType.StringType
                })
        ) {
            DetailScreen(navController = navController, viewModel = DetailScreenViewModel())
        }
    }
}
