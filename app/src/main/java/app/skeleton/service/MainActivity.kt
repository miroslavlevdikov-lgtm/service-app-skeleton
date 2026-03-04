package app.skeleton.service

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.skeleton.service.ui.composable.approot.AppRoot
import app.skeleton.service.ui.theme.ServiceSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceSkeletonTheme {
                AppRoot()
            }
        }
    }
}