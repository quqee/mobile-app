package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.ErrorContainer
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.S15_W400
import com.suslanium.hackathon.core.ui.theme.TextFieldOutline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    contentPaddingValues: PaddingValues = PaddingValues(all = 12.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = if (isError) ErrorContainer else Color.Transparent,
        unfocusedContainerColor = if (isError) ErrorContainer else Color.Transparent,
        disabledContainerColor = Color.Transparent,
        unfocusedBorderColor = TextFieldOutline,
        focusedBorderColor = TextFieldOutline,
        disabledBorderColor = TextFieldOutline,
        focusedTextColor = DarkBlue,
        unfocusedTextColor = DarkBlue,
        disabledTextColor = DarkBlue,
        focusedTrailingIconColor = DarkBlue,
        unfocusedTrailingIconColor = DarkBlue,
        disabledTrailingIconColor = DarkBlue,
        errorContainerColor = ErrorContainer,
        errorTrailingIconColor = Error,
        errorBorderColor = Error,
        errorCursorColor = Error,
        cursorColor = DarkBlue
    )
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        modifier = modifier.fillMaxWidth().padding(top = if(label.isNotBlank()) 8.dp else 0.dp),
        value = value,
        onValueChange = onValueChange,
        textStyle = S15_W400.copy(color = DarkBlue),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = true,
        cursorBrush = if (!isError) SolidColor(DarkBlue) else SolidColor(Error)
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(value = value,
            colors = colors,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = true,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            isError = isError,
            trailingIcon = trailingIcon,
            contentPadding = contentPaddingValues,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = RoundedCornerShape(10.dp),
                    focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                    unfocusedBorderThickness = 1.dp
                )
            },
            label = {
                Text(text = label, style = S15_W400, color = LightGray)
            })
    }
}