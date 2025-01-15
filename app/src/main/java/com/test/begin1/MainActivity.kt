package com.test.begin1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.test.begin1.navigation.PageNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
//        )

        setContent {
//            Begin1Theme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                PageNavigation(Modifier.padding(innerPadding))
            }
//            }

        }
    }
}

