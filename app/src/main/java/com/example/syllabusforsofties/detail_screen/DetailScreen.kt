package com.example.syllabusforsofties.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel
) {

    val courseScheme = mutableListOf(
        "間接型通信モデル",
        "物理時計の同期問題",
        "論理時計、イベントの順序付け",
        "論理時計、イベントの順序付け",
        "分散型排他制御",
        "選任問題",
        "複製に関する一貫性問題",
        "複製に関する一貫性問題",
        "複製に関する一貫性問題",
        "トランザクション処理と同時実行制御",
        "分散型トランザクション処理と同時実行制御",
        "トランザクション処理における障害からの回復処理",
        "トランザクション処理における障害からの回復処理",
        "分散合意 - ２相コミットプロトコル",
        "つなげ、まとめ"
    )

    val courseObjectives = mutableListOf(
        "理解度確認レポートと小テストの合格(6割)",
        "理解度確認レポートと小テストの合格または演習課題の合格(8割以上)"
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp), // パディングを追加
        verticalArrangement = Arrangement.spacedBy(8.dp) // テキスト間のスペースを追加
    ) {
        PlanBox(title = "講義科目名", content = "分散システム実践論")

        CoursePlanBox(title = "授業の計画", array = courseScheme)

        PlanBox(title = "配当年", content = "3年")

        PlanBox(title = "開講期間", content = "後期")

        PlanBox(title = "単位数", content = "2")

        PlanBox(title = "教科書", content = "特定のテキストは使用せず、適宜講義資料をファイル形式で配布する")

        CoursePlanBox(title = "目標", array = courseObjectives)

        PlanBox(title = "担当教員", content = "王　家宏")
    }

}

@Composable
fun PlanBox(title: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
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
fun CoursePlanBox(title: String, array: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column {
            Text(
                text = "$title:",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            // 1から15までのテキストを追加
            array.forEachIndexed { index, content ->
                Text(
                    text = "${index + 1}: $content", // 講義内容に置き換えてください
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
    DetailScreen(navController = rememberNavController(), DetailScreenViewModel())
}

