package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cexup.ui.corporate.screen.*
import com.cexup.ui.corporate.theme.CexupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CexupTheme {
//                val navController = rememberAnimatedNavController()
//                AnimatedNavHost(
//                    navController = navController,
//                    startDestination = "HOME",
//                ) {
//
//                }
                ScreenBabyChartGrowth()
            }
        }
    }
}
