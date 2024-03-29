package com.suslanium.hackathon.core.ui.common.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.common.ShortCard
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S15_W600
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.core.ui.theme.VeryLightGray
import java.util.UUID

@Composable
fun SelectEmailDialog(
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit,
    searchText: MutableState<String>,
    users: List<User>,
    selectedUser: MutableState<User?>
) {
    val filteredUsers = filterUsers(users, searchText.value)
    Dialog(
        onDismissRequest = {
            onCancelClick()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(715.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Выбрать исполнителя",
                    style = S20_W700,
                )
                Spacer(modifier = Modifier.height(16.dp))
                SearchRow(
                    text = "Поиск", searchText = searchText
                )
                Spacer(modifier = Modifier.height(16.dp))

                var confirm by remember {
                    mutableStateOf("")
                }

                LazyColumn(Modifier.height(420.dp)) {
                    if (filteredUsers.isEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(id = R.string.not_found), style = S15_W600
                            )
                        }
                    } else {
                        items(filteredUsers.size) {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        if (selectedUser.value?.id == filteredUsers[it].id){
                                            selectedUser.value = null
                                            confirm = ""
                                        } else {
                                            selectedUser.value = filteredUsers[it]
                                            confirm = filteredUsers[it].email
                                        }
                                    }
                            ) {
                                ShortCard(
                                    title = filteredUsers[it].organizationName,
                                    desc = filteredUsers[it].email,
                                    containerColor =
                                        if (confirm == filteredUsers[it].email){
                                            VeryLightGray
                                        }
                                        else{
                                            Color.White
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(
                    text = "Подтвердить",
                    onClick = onConfirmClick
                )
            }
        }
    }
}

data class User(
    val id: UUID,
    val fullName: String,
    val email: String,
    val organizationId: UUID,
    val organizationName: String
)


@Composable
fun SearchRow(
    text: String,
    searchText: MutableState<String>,
) {
    TextField(value = searchText.value, onValueChange = { searchText.value = it }, label = {
        Text(
            text, style = S14_W400, color = Primary.copy(alpha = 0.5f)
        )
    }, leadingIcon = {
        Icon(
            Icons.Default.Search, contentDescription = null, tint = Primary.copy(alpha = 0.5f)
        )
    }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    ), singleLine = true, textStyle = S14_W400
    )
}

fun filterUsers(users: List<User>, searchText: String): List<User> {
    return users.filter { user ->
        user.email.contains(searchText, ignoreCase = true)
    }
}

@Preview
@Composable
private fun SelectWorkerDialogPreview() {
    val searchText = remember { mutableStateOf("") }
    val selectedUser = remember { mutableStateOf<User?>(null) }
    SelectEmailDialog(
        onConfirmClick = {},
        onCancelClick = {},
        searchText = searchText,
        users = listOf(
            User(
                UUID.randomUUID(),
                "Иванов Иван Иванович",
                "mail@mail.ru",
                UUID.randomUUID(),
                "ООО Рога и копыта"
            ), User(
                UUID.randomUUID(),
                "Петров Петр Петрович",
                "mail@mail.ru",
                UUID.randomUUID(),
                "ООО Рога и копыта"
            ), User(
                UUID.randomUUID(),
                "Сидоров Сидор Сидорович",
                "mail@mail.ru",
                UUID.randomUUID(),
                "ООО Рога и копыта"
            )
        ),
        selectedUser = selectedUser
    )
}
