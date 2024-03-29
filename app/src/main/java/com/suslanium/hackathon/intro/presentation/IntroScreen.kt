package com.suslanium.hackathon.intro.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.defect.presentation.ui.components.CustomIndicator
import kotlinx.coroutines.launch

data class IntroPage(val image: Int, val title: String, val description: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(onNavigateAfterSuccess: () -> Unit) {
    val pages = listOf(
        IntroPage(
            R.drawable.road_construction,
            stringResource(R.string.intro_1_title),
            stringResource(R.string.intro_1_desc)
        ), IntroPage(
            R.drawable.teamwork,
            stringResource(R.string.intro_2_title),
            stringResource(R.string.intro_2_desc)
        ), IntroPage(
            R.drawable.surveyor,
            stringResource(R.string.intro_3_title),
            stringResource(R.string.intro_3_desc)
        )
    )
    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.skip),
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
                .clickable { onNavigateAfterSuccess() },
            style = S14_W400,
            color = BlueGray
        )
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalPager(state = pagerState) { page ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = pages[page].image), contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(Modifier.height(72.dp)) {
                    Text(
                        text = pages[page].title,
                        style = S20_W700,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pages[page].description,
                    style = S14_W400.copy(textAlign = TextAlign.Center),
                    color = BlueGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        CustomIndicator(
            count = 3,
            pagerState = pagerState,
            radius = CornerRadius(16f),
            circleSpacing = 12.dp,
            activeLineWidth = 16.dp,
            width = 6.dp,
            height = 6.dp
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = if (pagerState.currentPage == 2) stringResource(R.string.lets_start) else stringResource(
                R.string.next
            ),
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                if (pagerState.currentPage == 2) {
                    onNavigateAfterSuccess()
                } else coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            },
        )
    }
}


@Preview
@Composable
private fun IntroPreview() {
    IntroScreen {}
}