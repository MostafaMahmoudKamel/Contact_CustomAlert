package com.example.contact_21in.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Input(
    stateInput: MutableState<String>,
    errorState: MutableState<Boolean>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = stateInput.value,
        onValueChange = { stateInput.value = it },
        label = { Text(text = label) },
        modifier = modifier.then(
            Modifier
                .padding(8.dp)
                .wrapContentHeight()
        ),
        isError = errorState.value
    )
}