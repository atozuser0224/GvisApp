package com.example.gvisapp.composable.util

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gvisapp.MainActivity.Companion.isTextFieldFocused
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun def_TextField(text:MutableState<String>, modifier: Modifier,label:String,icon:ImageVector,shape:Shape,onClick:()->Unit, onChange: (String) -> Unit) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    TextField(
        value = text.value,
        onValueChange = onChange,
        modifier
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                isTextFieldFocused = it.isFocused
            },
        singleLine = true,
        shape = shape,
        keyboardOptions = KeyboardOptions.Default
        , colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ), label = { Text(text = label) }, trailingIcon = { IconButton(onClick =onClick) {
            Icon(
                imageVector = icon,
                contentDescription = "textFieldIcon"
            )
        }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun underLine_TextField(text:MutableState<String>, modifier: Modifier,label:@Composable (()->Unit),icon:ImageVector?,shape:Shape,onClick:()->Unit, onChange: (String) -> Unit) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    OutlinedTextField(
        value = text.value,
        onValueChange = onChange,
        modifier = modifier
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                isTextFieldFocused = it.isFocused
            },
        singleLine = true,
        shape = shape,
        keyboardOptions = KeyboardOptions.Default
        ,
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center), trailingIcon = {
            icon?.let {
                IconButton(onClick =onClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = "textFieldIcon"
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun round_TextField(text:MutableState<String>, modifier: Modifier, label:String, icon: ImageVector, shape:Shape, onChange: (String) -> Unit,keyboardOptions: KeyboardOptions,visualTransformation: VisualTransformation= VisualTransformation.None,onClick: () -> Unit = {}) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    TextField(value = text.value,modifier = modifier
        .focusRequester(focusRequester = focusRequester)
        .onFocusChanged {
            isTextFieldFocused = it.isFocused
        }, onValueChange = onChange,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = shape,
        label = { Text(text = label, textAlign = TextAlign.Center) }, trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    icon,
                    contentDescription = "textFieldIcon"
                )
            }
        },
        keyboardOptions = keyboardOptions)
}
