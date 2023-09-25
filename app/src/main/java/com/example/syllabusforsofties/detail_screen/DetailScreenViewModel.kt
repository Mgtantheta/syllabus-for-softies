package com.example.syllabusforsofties.detail_screen
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.syllabusforsofties.data.Course
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DetailScreenViewModel : ViewModel() {
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
}
