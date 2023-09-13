package com.example.syllabusforsofties.detail_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syllabusforsofties.navigation.Screen

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel
) {
    Log.d("tag", viewModel.docRef.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f) // 垂直方向のスペースを均等に分配
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column {
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screen.Home.route)
                    },
                    text = "Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
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
