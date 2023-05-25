package com.example.jetnote.componets

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onItemAction: () -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        textStyle = LocalTextStyle.current.copy(background = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onItemAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )



}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick:()-> Unit,
    enabled:Boolean=true,
    backgroundColor: Color = Color.Yellow
){
    Button(onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = if (backgroundColor == Color.Yellow) Color.Black else Color.White
        )
    ) {
        Text(text = text)
    }
}