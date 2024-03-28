package com.suslanium.hackathon.core.ui.common.dialogs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S16_W700

@Composable
fun ConfirmDialog(
    showDialog: MutableState<Boolean>,
    onConfirmClick: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(onDismissRequest = { showDialog.value = false }, title = {
        Text(
            text = "Выйти из аккаунта?", style = S16_W700
        )
    }, text = {
        Text(
            text = "Вы уверены, что хотите выйти из аккаунта?", style = S14_W400
        )
    }, confirmButton = {
        ClickableText(
            AnnotatedString("Выйти"), onClick = {
                onConfirmClick()
                showDialog.value = false
            }, style = S14_W400.copy(color = Primary)
        )
    }, dismissButton = {
        ClickableText(
            AnnotatedString("Отмена"),
            onClick = {
                onDismissRequest()
                showDialog.value = false
            },
            style = S14_W400.copy(color = Primary),
        )
    }, shape = RoundedCornerShape(10.dp)
    )

}

@Preview
@Composable
private fun ConfirmDialogPreview() {
    val showDialog = remember { mutableStateOf(true) }
    ConfirmDialog(
        showDialog = showDialog,
    )
}