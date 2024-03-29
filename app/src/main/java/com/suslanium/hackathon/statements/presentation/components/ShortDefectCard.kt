package com.suslanium.hackathon.statements.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.PaddingSmall
import com.suslanium.hackathon.core.ui.theme.S11_W500
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S16_W700
import com.suslanium.hackathon.defect.presentation.ui.components.observeAsState
import com.suslanium.hackathon.statements.data.model.ShortDefectDto
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error

@Composable
fun ShortDefectCard(
    defect: ShortDefectDto,
    modifier: Modifier = Modifier
) {
    val lifeCycleState by LocalLifecycleOwner.current.lifecycle.observeAsState()
    val context = LocalContext.current
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
            Lifecycle.Event.ON_RESUME -> com.suslanium.hackathon.defect.presentation.ui.components.searchManager.submit(
                Point(defect.latitude, defect.longitude), 17, SearchOptions(), searchSessionListener
            )

            else -> Unit
        }
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = defect.status.iconId),
                    contentDescription = null,
                    tint = defect.status.color
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = defect.status.description,
                    style = S11_W500,
                    color = defect.status.color
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = defect.type,
                style = S16_W700,
                color = DarkBlue
            )

            Spacer(modifier = Modifier.height(PaddingSmall))

            Text(
                text = defect.description ?: addressValue,
                style = S14_W400,
                color = BlueGray
            )
        }
    }
}