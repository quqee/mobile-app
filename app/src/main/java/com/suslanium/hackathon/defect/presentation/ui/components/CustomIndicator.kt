package com.suslanium.hackathon.defect.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.Primary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    pagerState: PagerState,
    radius: CornerRadius,
    circleSpacing: Dp,
    activeLineWidth: Dp,
    width: Dp,
    height: Dp
) {
    Canvas(modifier = modifier) {
        val spacing = circleSpacing.toPx()
        val dotWidth = width.toPx()
        val dotHeight = height.toPx()

        val activeDotWidth = activeLineWidth.toPx()

        // Calculate total width of all indicators including the spacing
        val totalWidth = (dotWidth * count) + (spacing * (count - 1))

        // Calculate the starting x position to center the indicators
        var x = (size.width - totalWidth) / 2 - 25
        val y = center.y

        repeat(count) { i ->
            val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()

            val factor = (dotOffset * (activeDotWidth - dotWidth))

            val calculatedWidth = when {
                i == current -> activeDotWidth - factor
                i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                else -> dotWidth
            }

            val color = if (i == pagerState.currentPage) Primary else LightGray
            drawIndicator(x, y, calculatedWidth, dotHeight, radius, color)
            x += calculatedWidth + spacing
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffsetFraction

private fun DrawScope.drawIndicator(
    x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, color: Color
) {
    val rect = RoundRect(
        x, y - height / 2, x + width, y + height / 2, radius
    )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = color)
}