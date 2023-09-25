package com.example.syllabusforsofties.home_screen

import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    val courseThumbnailName = mutableListOf(
        // Week
        "",
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",

        //
        "1限\n8:50\n" +
                "~\n10:20",
        "",
        "",
        "",
        "",
        "",

        // 二限
        "2限\n10:30\n" +
                "~\n12:00",
        listOf(
            "自然言語処理",
            "分散システム実践論",
        ),
        "統計解析Ⅱ",
        "コース演習Ⅱ(水2)",
        listOf(
            "メディア論",
            "数値計算の理論と実際"
        ),
        "応用情報システム学",

        //3限
        "3限\n13:00\n" +
                "~\n14:30",
        "",
        "",
        "プロジェクト演習Ⅱ",
        "組込みシステム論",
        "セキュリティ論",

        // 4限
        "4限\n14:40\n" +
                "~\n16:10",
        "",
        "専門英語Ⅱ",
        "",
        "経営情報学",
        "機械学習",

        // 5限
        "5限\n16:20\n" +
                "~\n17:50",
        listOf(
            "CG幾何学",
            "統合情報システム学Ⅰ",
        ),
        "コース演習Ⅱ(火5)",
        "",
        "システムデザインPBL",
        listOf(
            "マイクロコンピュータ制御",
            "情報環境論"
        )
    )

    //遷移しないリスト
    val notClickableList = mutableListOf<String>(
        "",
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",
        "1限\n8:50~10:20",
        "2限\n10:30~12:00",
        "3限\n13:00~14:30",
        "4限\n14:40~16:10",
        "5限\n16:20~17:50",
    )


}
