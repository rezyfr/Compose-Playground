package io.rezyfr.muviplayground.navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.rezyfr.muviplayground.presentation.MuviApp
import io.rezyfr.muviplayground.ui.theme.MuviPlaygroundTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var backPressed = 0L

    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuviApp()
        }
    }
}