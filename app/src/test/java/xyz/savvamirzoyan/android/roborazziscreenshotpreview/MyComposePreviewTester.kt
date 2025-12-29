package xyz.savvamirzoyan.android.roborazziscreenshotpreview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.github.takahirom.roborazzi.AndroidComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester.TestParameter.JUnit4TestParameter.AndroidPreviewJUnit4TestParameter
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import io.github.classgraph.AnnotationInfoList
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview


/**
 * Wrapper around ComposablePreview<AndroidPreviewInfo> which sets LocalInspectionMode to true
 * so that snapshots render exactly the same as previews.
 */
data class InspectableComposablePreviewWrapper(
    val delegate: ComposablePreview<AndroidPreviewInfo>,
    override val declaringClass: String = delegate.declaringClass,
    override val methodName: String = delegate.methodName,
    override val otherAnnotationsInfo: AnnotationInfoList? = delegate.otherAnnotationsInfo,
    override val previewIndex: Int? = delegate.previewIndex,
    override val previewInfo: AndroidPreviewInfo = delegate.previewInfo,
) : ComposablePreview<AndroidPreviewInfo> {
    @Composable
    override fun invoke() {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            delegate.invoke()
        }
    }
}

@Suppress("unused")
@OptIn(ExperimentalRoborazziApi::class)
class MyComposePreviewTester : ComposePreviewTester<AndroidPreviewJUnit4TestParameter> {

    private val delegate = AndroidComposePreviewTester()

    override fun testParameters() = delegate
        .testParameters()
        .map { it.copy(preview = InspectableComposablePreviewWrapper(it.preview)) }

    override fun test(testParameter: AndroidPreviewJUnit4TestParameter) {
        try {
            delegate.test(testParameter)
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }
}
