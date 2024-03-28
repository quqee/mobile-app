package com.suslanium.hackathon.defect.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.Status
import com.suslanium.hackathon.core.ui.common.StatusElement
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.core.ui.theme.White

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DefectScreen() {
    val state = rememberPagerState(pageCount = { 3 })
    val status = Status.IN_PROGRESS
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            contentColor = White,
            containerColor = Primary,
            onClick = { },
        ) {
            Icon(Icons.Filled.Edit, "Floating action button.")
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                HorizontalPager(
                    state = state, modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null
                    )
                }
                CustomIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    count = state.pageCount,
                    pagerState = state,
                    radius = CornerRadius(16f),
                    circleSpacing = 12.dp,
                    activeLineWidth = 16.dp,
                    width = 6.dp,
                    height = 6.dp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Дефект",
                style = S20_W700,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.Route, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Поперечная трещина")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Вожовский переулок, Кромы, Стрелецкое сельское поселение, Кромской район, Орловская область, Центральный федеральный округ, 303210, Россия")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Default.ViewInAr, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Вожовский переулок, Кромы, Стрелецкое сельское поселение, Кромской район, Орловская область, Центральный федеральный округ, 303210, Россия")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // TODO add map
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            StatusElement(modifier = Modifier.padding(start = 16.dp), status = status)
            if (status == Status.DONE) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Результаты работы",
                    style = S20_W700,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 16.dp, end = 16.dp
                    )
                ) {
                    items(4) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = Modifier
                                .height(140.dp)
                                .width(157.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                        )
                        if (it != 3) Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun DefectScreenPreview() {
    DefectScreen()
}