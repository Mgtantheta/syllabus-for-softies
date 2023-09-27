package com.example.syllabusforsofties.detail_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.syllabusforsofties.data.Course


@Composable
fun DetailScreen(
    navController: NavController,
    courseNamePram: String,
    viewModel: DetailScreenViewModel
) {
    // HomeScreenからNavigateされたデータを取得する
    val backStackEntry = navController.currentBackStackEntryAsState()
    val arguments = backStackEntry.value?.arguments
    val courseName = arguments?.getString("courseName") ?: ""

    val courseIDState = remember { mutableStateOf<String?>(null) }
    val courseState = remember { mutableStateOf<Course?>(null) }

    Log.d("Tag", "courseName: $courseName")

    // courseIDを取得するコルーチンを起動
    LaunchedEffect(key1 = courseName) {
        val courseID = viewModel.getCourseIDByCourseName(courseName = courseName)
        courseIDState.value = courseID
    }

    // courseIDが取得できたら、courseを取得するコルーチンを起動
    LaunchedEffect(key1 = courseIDState.value) {
        val courseID = courseIDState.value
        if (courseID != null) {
            val course = viewModel.getCourse(courseId = courseID)
            Log.d("Tag", "course: $course")
            courseState.value = course
            Log.d("Tag", "courseState.courseName: ${courseState.value?.courseName}")
        }
    }
    Log.d("Tag", "courseState.courseName2: ${courseState.value?.courseName}")

    DetailScreenContent(course = courseState.value)
}

@Composable
fun DetailScreenContent(course: Course?) {
    // DocumentSnapshot to Course

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp), // パディングを追加
        verticalArrangement = Arrangement.spacedBy(8.dp) // テキスト間のスペースを追加
    ) {
        Log.d("Tag", "In DetailScreenContent ${course?.courseName}")
        PlanBox(title = "講義科目名", content = course?.courseName ?: "")

        CoursePlanBox(title = "担当教員", array = course?.instructors ?: listOf())

        PlanBox(title = "配当年", content = course?.dividendYear ?: "")

        PlanBox(title = "開講場所", content = course?.place ?: "")

        PlanBox(title = "開講期間", content = course?.term ?: "")

        PlanBox(title = "単位数", content = course?.credits ?: "")

        PlanBox(title = "教科書", content = course?.textbook ?: "")

        CoursePlanBox(title = "授業の計画", array = course?.coursePlan ?: listOf())

        CoursePlanBox(title = "成績評価の方法", array = course?.gradingMethod ?: listOf())
    }
}

@Composable
fun PlanBox(title: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "$title:",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = content,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CoursePlanBox(title: String, array: List<String>?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "$title:",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            // 1から15までのテキストを追加
            array?.forEachIndexed { index, content ->
                Text(
                    text = "${index + 1}: $content",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), courseNamePram = "", viewModel = DetailScreenViewModel())
}
