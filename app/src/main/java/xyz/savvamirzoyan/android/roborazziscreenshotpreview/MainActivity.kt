package xyz.savvamirzoyan.android.roborazziscreenshotpreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import xyz.savvamirzoyan.android.roborazziscreenshotpreview.ui.theme.RoborazziScreenshotPreviewTheme
import kotlin.compareTo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoborazziScreenshotPreviewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(7) {
            EvenlySizedNumber(
                modifier = Modifier.weight(1f),
                text = (it + 1).toString()
            )
        }
    }
}

@Preview
@PreviewLightDark
@PreviewFontScale
@PreviewDynamicColors
@Composable
fun GreetingScreenPreview() {
    MaterialTheme {
        GreetingScreen()
    }
}

@Composable
fun EvenlySizedNumber(
    modifier: Modifier,
    text: String
) {

    val density = LocalDensity.current.density
    var height by remember { mutableStateOf(Dp.Unspecified) }

    Text(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .onSizeChanged { size ->
                height = min(size.width.div(density).toInt().dp, 56.dp)
            }
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .wrapContentSize(),
        text = text,
        color = MaterialTheme.colorScheme.onPrimary
    )
}