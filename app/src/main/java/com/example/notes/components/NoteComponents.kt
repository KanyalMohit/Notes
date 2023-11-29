package com.example.notes.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier=Modifier,
    text:String,
    label: String,
    maxLine: Int =1,
    onTextChange:(String) -> Unit,
    onImeAction: () -> Unit={},
    imeAction: ImeAction = ImeAction.Done


) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = Modifier
    )

}

@Composable
fun NoteButton(
    modifier: Modifier=Modifier,
    text: String,
    onClick:() ->Unit,
    enabled: Boolean = true
    ) {
    Button(onClick = onClick
    , shape = CircleShape ,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64C2C2))
    ) {
        Text(text, color = Color.Black)
    }
}

@Composable
fun ColorButton(modifier: Modifier=Modifier,
                color: IconButtonColors,
                onClick: () -> Unit,
                enabled: Boolean = true
                ) {
            FilledIconButton(onClick = onClick, colors = color, shape = CircleShape, enabled = enabled) {

            }

}
