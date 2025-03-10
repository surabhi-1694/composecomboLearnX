package com.example.android2o.uipackage

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Spacer(){

}
@Composable
fun Spacer1(){
    Spacer(Modifier.height(1.dp))
}


@Composable
fun Spacer8(){
    Spacer(Modifier.height(8.dp))
}

@Composable
fun SpacerValue(value:Int){
    Spacer(Modifier.height(value.dp))
}

@Composable
fun CircularProgressBar(color: Color, i: Int) {
    Box(modifier = Modifier.fillMaxWidth().height(i.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = color )
    }
}


@Composable
fun Modifier.verticalScrollbar(state: ScrollState, scrollbarWidth: Dp = 6.dp, color: Color = Color.Blue): Modifier{
    val alpha by animateFloatAsState(targetValue = if(state.isScrollInProgress) 1f else 0f,
        animationSpec = tween(400, delayMillis = if(state.isScrollInProgress) 0 else 700)
    )

    return this then Modifier.drawWithContent {
        drawContent()


        val viewHeight = state.viewportSize.toFloat()
        val contentHeight = state.maxValue + viewHeight

        val scrollbarHeight = (viewHeight * (viewHeight / contentHeight )).coerceIn(10.dp.toPx() .. viewHeight)
        val variableZone = viewHeight - scrollbarHeight
        val scrollbarYoffset = (state.value.toFloat() / state.maxValue) * variableZone

        drawRoundRect(
            cornerRadius = CornerRadius(scrollbarWidth.toPx() / 2, scrollbarWidth.toPx() / 2),
            color = color,
            topLeft = Offset(this.size.width - scrollbarWidth.toPx(), scrollbarYoffset),
            size = Size(scrollbarWidth.toPx(), scrollbarHeight),
            alpha = alpha
        )
    }
}