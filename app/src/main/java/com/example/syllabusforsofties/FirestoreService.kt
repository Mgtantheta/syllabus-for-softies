package com.example.syllabusforsofties

import android.util.Log
import com.example.syllabusforsofties.data.Course
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

// firestoreと通信するクラス
class FirestoreService {
    val db = Firebase.firestore

    suspend fun read(docNo: String): Course? {
        val docRef = db.collection("courses").document(docNo)

        return try {
            val document = docRef.get().await()
            if (document.exists()) {
                val term: String? = document.getString("開講期間")
                val credits: String? = document.getString("単位数")
                val coursePlan: Map<String, String>? = document.get("授業の計画") as? Map<String, String>
                val textbook: String? = document.getString("教科書")
                val gradingMethod: Map<String, String>? = document.get("成績評価の方法") as? Map<String, String>
                val courseName: String? = document.getString("講義科目名称")
                val instructors: List<String>? = document.get("担当教員") as? List<String>

                Course(
                    term = term,
                    credits = credits,
                    coursePlan = coursePlan,
                    textbook = textbook,
                    gradingMethod = gradingMethod,
                    courseName = courseName,
                    instructors = instructors
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
