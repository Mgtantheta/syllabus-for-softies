package com.example.syllabusforsofties.home_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syllabusforsofties.FirestoreService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.syllabusforsofties.data.FirestoreCourse


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri")


    Column {
        // 曜日のタイトルを表示するLazyRow
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(daysOfWeek) { day ->
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
            }
        }
        // グリッドのセルを表示するLazyVerticalGrid
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(25) {
                // 各セルの内容をここで定義
                GridCellItem(
                    navController = navController,
                    courseNames = viewModel.courseThumbnailName[it]
                )
            }
        }
    }
}

@Composable
fun GridCellItem(navController: NavController, courseNames: Any) {
    val firestoreService = FirestoreService()
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f / 2f)
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
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                // セルをクリックしたときの処理をここに記述
                                scope.launch {
                                    firestoreService.read(docNo = FirestoreCourse.C01.courseId)
                                }
                                navController.navigate("detail_screen")
                            }
                    )
                }

                is List<*> -> {
                    // courseNamesがListの場合
                    for (courseName in courseNames) {
                        Text(
                            text = courseName as? String ?: "",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    scope.launch {
                                        firestoreService.read(docNo = FirestoreCourse.C02.courseId)
                                    }
                                    navController.navigate("detail_screen")
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
        viewModel = HomeScreenViewModel()
    )
}
