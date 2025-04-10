package com.tribelle.giziku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.tribelle.giziku.ui.screen.AppNavGraph
import com.tribelle.giziku.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            AppNavGraph(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
