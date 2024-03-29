package com.suslanium.hackathon.assignment.presentation

import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.assignment.playback.AndroidAudioPlayer
import com.suslanium.hackathon.assignment.recorder.AndroidAudioRecorder
import com.suslanium.hackathon.core.localDateTimeToDate
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.common.StatementFullCard
import com.suslanium.hackathon.core.ui.common.dialogs.SelectEmailDialog
import com.suslanium.hackathon.core.ui.common.dialogs.User
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.S16_W700
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.statements.presentation.state.StatementUiState
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File
import java.util.Date
import java.util.UUID


@Composable
fun Assignment(
    statementId: UUID,
    onConfirm: () -> Unit
){
    val context = LocalContext.current
    var file: File
    val recorder by lazy {
        AndroidAudioRecorder(context.applicationContext)
    }
    val player by lazy {
        AndroidAudioPlayer(context.applicationContext)
    }
    val model: AssignmentViewModel = koinViewModel()
    model.cacheDir = context.cacheDir

    val viewModel: StatementViewModel = koinViewModel(parameters = { parametersOf(statementId.toString()) })
    val uiState by viewModel.statementUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(
                horizontal = 16.dp,
            )
            .verticalScroll(
                rememberScrollState()
            )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                )
                .background(Color.White),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    ),
                text = stringResource(id = R.string.assignment),
                textAlign = TextAlign.Center,
                style = S20_W700,
                color = DarkBlue
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    ),
                text = stringResource(id = R.string.assignment_on),
                textAlign = TextAlign.Center,
                style = S16_W700,
                color = DarkBlue
            )
        }
        when (uiState) {
            StatementUiState.Initial -> Unit
            StatementUiState.Loading -> Unit
            StatementUiState.Error -> TODO()
            is StatementUiState.Success -> {
                val statement = (uiState as StatementUiState.Success).statement
                StatementFullCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 10.dp
                        ),
                    roadName = statement.areaName,
                    category = statement.roadType.description,
                    date = statement.createTime.localDateTimeToDate(),
                    length = statement.length.toString(),
                    roadType = statement.roadType.description,
                    direction = statement.direction
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                ),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    ),
                text = stringResource(id = R.string.record_info),
                style = S16_W700,
                color = DarkBlue
            )
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ){request ->
                if (request) {
                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            var recordState by remember {
                mutableIntStateOf(0)
            }
            var playState by remember {
                mutableStateOf(false)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MyTextField(
                    value = when (recordState){
                        0 -> {
                            stringResource(id = R.string.record)
                        }
                        1 -> {
                            stringResource(id = R.string.recording)
                        }
                        else -> {
                            stringResource(id = R.string.your_ass)
                        }
                    },
                    onValueChange = {},
                    enabled = false,
                    trailingIcon = {
                        when (recordState) {
                            0 -> {
                                Icon(
                                    painter = painterResource(id = R.drawable.micro),
                                    contentDescription = "micro"
                                )
                            }
                            1 -> {
                                Icon(
                                    imageVector = Icons.Default.Circle,
                                    contentDescription = "record",
                                    tint = Color.Red
                                )
                            }
                            else -> {
                                if (playState){
                                    Icon(
                                        imageVector = Icons.Default.Pause,
                                        contentDescription = "pause",
                                        tint = BlueGray
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "play",
                                        tint = BlueGray
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .clickable {
                            val permissionCheckResult = ContextCompat.checkSelfPermission(
                                context, android.Manifest.permission.RECORD_AUDIO
                            )
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                when (recordState) {
                                    0 -> {
                                        file = File(model.cacheDir, "audio.mp3")
                                        file.also { f ->
                                            recorder.start(f)
                                            model.audioFile = f
                                        }
                                        recordState = 1
                                    }
                                    1 -> {}
                                    else -> {
                                         if (playState){
                                            player.stop()
                                            playState = !playState
                                        } else {
                                            model.audioFile?.let { it1 -> player.playFile(it1) }
                                            playState = !playState
                                        }
                                    }
                                }
                            } else {
                                permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                            }
                        }
                )
                when (recordState) {
                    0 -> {}
                    1 -> {
                        Icon(
                            imageVector = Icons.Default.Pause,
                            contentDescription = "pause",
                            tint = Color.Red,
                            modifier = Modifier
                                .clickable {
                                    recorder.stop()
                                    recordState = 2
                                }
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            tint = Color.Red,
                            modifier = Modifier
                                .clickable {
                                    model.audioFile = null
                                    recordState = 0
                                }
                        )
                    }
                }
            }
            val searchText = remember { mutableStateOf("") }
            val selectedUser = remember { mutableStateOf<User?>(
                User(
                    id = UUID.randomUUID(),
                    fullName = "",
                    email = "Назначить",
                    organizationId = UUID.randomUUID(),
                    organizationName = ""
                )
            ) }
            val dialogState = remember {
                mutableStateOf(false)
            }
            Column(
                Modifier.padding(
                    vertical = 10.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Назначьте организацию-исполнителя, которая сможет заниматься устранением дефектов",
                    style = S16_W700,
                    color = DarkBlue
                )
                MyTextField(
                    value = selectedUser.value!!.email,
                    onValueChange = {},
                    modifier = Modifier
                        .clickable {
                            dialogState.value = true
                        },
                    enabled = false,
                    trailingIcon = {
                        if (selectedUser.value?.fullName == "") {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add",
                                tint = DarkBlue
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Create,
                                contentDescription = "create",
                                tint = DarkBlue
                            )
                        }
                    }
                )
            }
            if (dialogState.value){
                SelectEmailDialog(
                    onConfirmClick = {
                        dialogState.value = false
                    },
                    onCancelClick = {
                        dialogState.value = false
                    },
                    searchText = searchText,
                    users = listOf(
                        User(
                            UUID.randomUUID(),
                            "Иванов Иван Иванович",
                            "mail1@mail.ru",
                            UUID.randomUUID(),
                            "ООО Рога и копыта"
                        ), User(
                            UUID.randomUUID(),
                            "Петров Петр Петрович",
                            "mail2@mail.ru",
                            UUID.randomUUID(),
                            "ООО Рога и копыта"
                        ), User(
                            UUID.randomUUID(),
                            "Сидоров Сидор Сидорович",
                            "mail3@mail.ru",
                            UUID.randomUUID(),
                            "ООО Рога и копыта"
                        )
                    ),
                    selectedUser = selectedUser
                )
            }

            val mYear: Int
            val mMonth: Int
            val mDay: Int
            val mCalendar = Calendar.getInstance()
            mYear = mCalendar.get(Calendar.YEAR)
            mMonth = mCalendar.get(Calendar.MONTH)
            mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

            mCalendar.time = Date()

            val mDate = remember { mutableStateOf("Выбрать дату") }

            val mDatePickerDialog = android.app.DatePickerDialog(
                context,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
                }, mYear, mMonth, mDay
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    ),
                text = "Укажите дату, к которой поручение должно быть выполнено",
                style = S16_W700,
                color = DarkBlue
            )
            MyTextField(
                value = mDate.value,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.clickable {
                    mDatePickerDialog.show()
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = "calendar",
                        tint = DarkBlue
                    )
                }
            )
        }

        PrimaryButton(
            text = "Создать",
            onClick = {
                onConfirm()
            }
        )
    }
}