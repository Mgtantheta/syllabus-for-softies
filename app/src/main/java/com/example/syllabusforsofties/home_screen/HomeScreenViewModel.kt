package com.example.syllabusforsofties.home_screen

import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {
    val courseThumbnailName = mutableListOf(
        // 一限
        "",
        "",
        "",
        "",
        "",

        // 二限
        "自然言語処理\n\n分散システム実践論",
        "統計解析Ⅱ",
        "コース演習Ⅱ",
        "メディア論\n\n数値計算の理論と実際",
        "応用情報システム学",

        //3限
        "",
        "",
        "プロジェクト演習Ⅱ",
        "組込みシステム論",
        "セキュリティ論",

        // 4限
        "",
        "専門英語Ⅱ",
        "",
        "経営情報学",
        "機械学習",

        // 5限
        "CG幾何学\n\n統合情報システム学Ⅰ",
        "コース演習Ⅱ",
        "",
        "システムデザインPBL",
        "マイクロコンピュータ制御"
    )
}
