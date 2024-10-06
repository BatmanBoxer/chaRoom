package com.batman.charoom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.batman.charoom.rootNavigation.RootNavGraph
import com.batman.charoom.ui.theme.ChaRoomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            ChaRoomTheme {
                val backgroundColor = MaterialTheme.colorScheme.background
                window.statusBarColor = backgroundColor.toArgb()
                RootNavGraph()
            }
        }
    }
}



