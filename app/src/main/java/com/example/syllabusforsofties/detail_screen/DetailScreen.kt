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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Composable
fun DetailScreen(
    navController: NavController,
    courseNamePram: String
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
        val courseID = getCourseIDByCourseName(courseName = courseName)
        courseIDState.value = courseID
    }

    // courseIDが取得できたら、courseを取得するコルーチンを起動
    LaunchedEffect(key1 = courseIDState.value) {
        val courseID = courseIDState.value
        if (courseID != null) {
            val course = getCourse(courseId = courseID)
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
    DetailScreen(navController = rememberNavController(), courseNamePram = "")
}

suspend fun getCourseIDByCourseName(courseName: String): String? {
    // Firebase Firestoreから対応するドキュメントを検索
    val firestore = Firebase.firestore
    val querySnapshot = firestore.collection("courses")
        .whereEqualTo("講義科目名称", courseName)
        .get()
        .await()

    // ドキュメントが見つかった場合、最初のドキュメントのcourseIDを取得
    if (!querySnapshot.isEmpty) {
        val document = querySnapshot.documents.first()
        return document.id
    }

    // ドキュメントが見つからない場合はnullを返す
    return null
}

//get course by courseID
suspend fun getCourse(courseId: String): Course? {
    return withContext(Dispatchers.IO) {
        // Firestoreのコレクションにアクセスするための参照を作成
        val db = Firebase.firestore
        val docRef = db.collection("courses").document(courseId)

        try {
            val document = docRef.get().await()

            if (document.exists()) {
                val term: String? = document.getString("開講期間")
                val credits: String? = document.getString("単位数")
                val coursePlan: List<String>? = document.get("授業の計画") as List<String>?
                val textbook: String? = document.getString("教科書")
                val gradingMethod: List<String>? = document.get("成績評価の方法") as List<String>?
                val courseName: String? = document.getString("講義科目名称")
                val instructors: List<String>? = document.get("担当教員") as List<String>?
                val courseObjective: List<String>? = document.get("到達目標") as List<String>?
                val dividendYear: String? = document.getString("配当年")

                Course(
                    courseName = courseName,
                    term = term,
                    credits = credits,
                    coursePlan = coursePlan,
                    textbook = textbook,
                    gradingMethod = gradingMethod,
                    instructors = instructors,
                    courseObjectives = courseObjective,
                    dividendYear = dividendYear
                )
            } else {
                Log.d("TAG", "No such document")
                null
            }
        } catch (exception: Exception) {
            Log.d("TAG", "get failed with $exception")
            null
        }
    }
}
