package com.example.syllabusforsofties.detail_screen
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailScreenViewModel : ViewModel() {

    private val TAG = "DetailScreenViewModel"

    // Firestore インスタンスを初期化
    val db = Firebase.firestore
    val docRef = db.collection("courses").document("C01")

    init {
        docRef.get()
            .addOnSuccessListener { document ->
                onDocumentSuccess(document)
            }
            .addOnFailureListener { exception ->
                onDocumentFailure(exception)
            }
    }

    private fun onDocumentSuccess(document: DocumentSnapshot?) {
        if (document != null) {
            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

        } else {
            Log.d(TAG, "No such document")
        }
    }

    private fun onDocumentFailure(exception: Exception) {
        Log.d(TAG, "get failed with ", exception)
    }
}
