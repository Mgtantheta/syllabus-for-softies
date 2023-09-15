package com.example.syllabusforsofties

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope

// firestoreと通信するクラス
class FirestoreService {
    val db = Firebase.firestore

    suspend fun read(docNo: String) {
        val docRef = db.collection("courses").document(docNo)
        coroutineScope {
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d("TAG", "DocumentSnapshot data: ${document.data}")
//                        val term: String? = "ge",
//                        val credits: String? = "ge",
//                        val units: Int? = 0,
//                        val coursePlan: Map<String, String>? = "fef",
//                        val textbook: String? = document.getString("教科書") ?: "なし",
//                        val gradingMethod: Map<String, String>? = "fe"s,
//                        val courseName: String? = document.getString("講義科目名称"),
//                        val instructors: List<String>? = {"fejl", "ef"}
//                        val course = Course(
//                            term = term,
//                            credits = credits,
//                            units = units,
//                            coursePlan = coursePlan,
//                            textbook = textbook,
//                            gradingMethod = mapOf(),
//                            courseName = courseName,
//                            instructors = instructors
//                        )
                    } else {
                        Log.d("TAG", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("TAG", "get failed with ", exception)
                }
        }
    }
}
