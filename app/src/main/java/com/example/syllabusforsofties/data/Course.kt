package com.example.syllabusforsofties.data

data class Course(
    val courseName: String?, //授業名
    val term: String?, //開講期間
    val credits: String?, // 単位数
    val coursePlan: List<String>?, // 授業の計画
    val textbook: String?, // 教科書
    val gradingMethod: List<String>?, // 成績評価の方法
    val instructors: List<String>?, // 担当教員
    val courseObjectives: List<String>?, // 到達目標
    val dividendYear: String? // 配当年
)
