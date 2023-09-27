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
    viewModel: HomeScreenViewModel
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
            .aspectRatio(ratio = 1f / 3f)
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
                    Text(
                        text = courseNames, // 文字列に変換
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                if (courseNames in viewModel.notClickableList) return@clickable
                                navController.navigate("detail_screen/$courseNames")
                            }
                    )
                }
                is List<*> -> {
                    for (courseName in courseNames) {
                        if (courseName is String) {
                            Text(
                                text = courseName, // 文字列に変換
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
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
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = HomeScreenViewModel(),
    )
}
