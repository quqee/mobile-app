package com.suslanium.hackathon.defect.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import coil.compose.rememberAsyncImagePainter
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.Status
import com.suslanium.hackathon.core.ui.common.StatusElement
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.core.ui.theme.VeryLightGray
import com.suslanium.hackathon.core.ui.theme.White
import com.suslanium.hackathon.defect.data.model.DefectModel
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error

val searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DefectContent(
    model: DefectModel,
    onMarkAsDone: () -> Unit = {}
) {
    val lifeCycleState by LocalLifecycleOwner.current.lifecycle.observeAsState()
    val context = LocalContext.current
    val state = rememberPagerState(pageCount = { model.picturesBeforeRepair.size })
    var addressValue by remember {
        mutableStateOf(context.getString(R.string.loading))
    }
    val searchSessionListener = object : Session.SearchListener {
        override fun onSearchResponse(response: Response) {
            addressValue = if (response.collection.children.isNotEmpty()) {
                val name = response.collection.children[0].obj?.name
                val desc = response.collection.children[0].obj?.descriptionText
                if (name == null && desc == null) context.getString(R.string.error_address)
                else if (name != null && desc != null) "$name, $desc"
                else name ?: (desc ?: context.getString(R.string.error_address))
            } else context.getString(R.string.error_address)
        }

        override fun onSearchError(error: Error) {
            addressValue = context.getString(R.string.error_address)
        }
    }
    LaunchedEffect(lifeCycleState) {
        when (lifeCycleState) {
            Lifecycle.Event.ON_RESUME -> searchManager.submit(
                Point(model.latitude, model.longitude), 17, SearchOptions(), searchSessionListener
            )

            else -> Unit
        }
    }
    var shouldShowActionDialog by remember { mutableStateOf(false) }

    if (shouldShowActionDialog) {
        DefectActionDialog(onMarkAsDone = {
            shouldShowActionDialog = false
            onMarkAsDone()
        }, onCancelClick = {
            shouldShowActionDialog = false
        })
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                HorizontalPager(
                    state = state, modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = model.picturesBeforeRepair[it]),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(VeryLightGray),
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
                text = stringResource(id = R.string.defect),
                style = S20_W700,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = DarkBlue
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    Icons.Outlined.Route, contentDescription = null, tint = DarkBlue
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = model.defectType, color = DarkBlue
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = DarkBlue)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "$addressValue (${model.latitude}-${model.longitude})", color = DarkBlue
                )
            }

            if (model.defectDistance != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(Icons.Outlined.ViewInAr, contentDescription = null, tint = DarkBlue)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(
                            id = R.string.square_meters, model.defectDistance.toString()
                        ), color = DarkBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box {
                MapView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp)), startCameraPosition = CameraPosition(
                        Point(model.latitude, model.longitude), 17.0f, 150.0f, 30.0f
                    )
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.map_location_icon),
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.height(16.dp))
            StatusElement(
                modifier = Modifier.padding(start = 16.dp), status = model.status.statusAlternative
            )
            if (model.status.statusAlternative == Status.DONE) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.work_result),
                    style = S20_W700,
                    modifier = Modifier.padding(start = 16.dp),
                    color = DarkBlue
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 16.dp, end = 16.dp
                    )
                ) {
                    items(model.picturesAfterRepair.size) {
                        Image(
                            painter = rememberAsyncImagePainter(model = model.picturesAfterRepair[it]),
                            contentDescription = null,
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = Modifier
                                .height(140.dp)
                                .width(157.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(VeryLightGray)
                        )
                        if (it != model.picturesAfterRepair.size - 1) Spacer(
                            modifier = Modifier.width(
                                10.dp
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (model.status.statusAlternative != Status.DONE) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(all = 16.dp),
                contentColor = White,
                containerColor = Primary,
                onClick = {
                    shouldShowActionDialog = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        }
    }
}