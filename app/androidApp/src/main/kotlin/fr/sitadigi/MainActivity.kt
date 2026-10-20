package fr.sitadigi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.sitadigi.app.shared.ui.trips.TripListApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            // App()
            TripListApp(baseUrl = "http://10.0.2.2:3000")
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}