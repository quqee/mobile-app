package com.suslanium.hackathon.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.ui.theme.BlueGray
import com.suslanium.hackathon.ui.theme.Primary
import com.suslanium.hackathon.ui.theme.S14_W400
import com.suslanium.hackathon.ui.theme.S15_W600
import com.suslanium.hackathon.ui.theme.S20_W700

@Composable
fun ProfileScreen(viewModel: ProfileScreenViewModel) {
    val name by viewModel.name
    val company by viewModel.company
    val role by viewModel.companyRole

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(112.dp),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = name, style = S20_W700
        )
        Text(
            text = company, style = S14_W400, color = BlueGray
        )
        Text(text = role, style = S14_W400, color = BlueGray)

        Spacer(modifier = Modifier.height(5.dp))
        TextButton(
            onClick = { viewModel.logoutUser() }, colors = ButtonDefaults.textButtonColors(
                contentColor = Primary
            ), modifier = Modifier.fillMaxWidth(0.5f), shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Выйти из аккаунта", style = S15_W600
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {

}