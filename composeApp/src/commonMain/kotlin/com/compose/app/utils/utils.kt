import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Composable
fun getScreenSize(): DpSize {
    // 1. Get the raw pixel size from LocalWindowInfo
    val windowPixelSize: IntSize = LocalWindowInfo.current.containerSize

    // 2. Get the current density
    val density = LocalDensity.current

    // 3. Convert pixel dimensions to Dp using the density context
    return with(density) {
        // Use the .toDp() extension function on the Int values
        DpSize(windowPixelSize.width.toDp(), windowPixelSize.height.toDp())
    }
}

//@Composable
//fun GetScreenWidthDp(): Dp {
//    var screenWidthDp by remember { mutableStateOf(0.dp) }
//
//    BoxWithConstraints {
//        screenWidthDp = this.maxWidth
//    }
//
//    return screenWidthDp
//}