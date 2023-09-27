package com.example.syllabusforsofties.home_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel,
    onNavigateToDetailScreen: (String) -> Unit
) {
    Column{
        // グリッドのセルを表示するLazyVerticalGrid
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 6),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(36) {
                // 各セルの内容をここで定義
                GridCellItem(
                    navController = navController,
                    courseNames = viewModel.courseThumbnailName[it],
                    viewModel = viewModel(),
                )
            }
        }
    }

}

@Composable
fun GridCellItem(navController: NavController, courseNames: Any, viewModel: HomeScreenViewModel) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f / 3f)
            .shadow(4.dp)
            .background(Color.LightGray)
    ) {
        // セル内のコンテンツを表示
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            when (courseNames) {
                is String -> {
                    // courseNamesがStringの場合
                    Text(
                        text = courseNames,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                // courseNamesがcourse以外の時は遷移しない
                                if (courseNames in viewModel.notClickableList) return@clickable
                                navController.navigate("detail_screen/$courseNames")
                            }
                    )
                }
                is List<*> -> {
                    // courseNamesがListの場合
                    for (courseName in courseNames) {
                        Text(
                            text = courseName as? String ?: "",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate("detail_screen/$courseName")
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = HomeScreenViewModel(),
        onNavigateToDetailScreen = {}
    )
}
