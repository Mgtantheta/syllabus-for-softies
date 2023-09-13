package com.example.syllabusforsofties.data

data class Course(
    val term: String?,
    val credits: String?,
    val units: Int?,
    val coursePlan: Map<String, String>?,
    val textbook: String?,
    val gradingMethod: Map<String, String>?,
    val courseName: String?,
    val instructors: List<String>?
)
