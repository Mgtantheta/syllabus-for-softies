package com.example.syllabusforsofties.detail_screen
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DetailScreenViewModel : ViewModel() {
    // Firestore インスタンスを初期化
    val db = Firebase.firestore

    // ここにFirestoreからデータを取得する処理を書く
    fun getCourse(courseId: String): DocumentSnapshot? {
        var course: DocumentSnapshot? = null
        db.collection("courses").document(courseId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    course = document
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
            }
        return course
    }

    // courseNameからcourseIdを取得する処理を書く
    suspend fun getCourseIDByCourseName(courseName: String): String? {
        // Firebase Firestoreから対応するドキュメントを検索
        val querySnapshot = db.collection("courses")
            .whereEqualTo("courseName", courseName)
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
}
