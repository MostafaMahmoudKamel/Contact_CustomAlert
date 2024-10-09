package com.example.contact_21in.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.contact_21in.R
import com.example.contact_21in.ui.model.Contact


@Composable
fun DisplayContactOnScreen(
    contact: Contact,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.Red)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(R.drawable.desha2),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Column {
                Text(text = contact.name)
                Text(text = contact.phone)
                Text(text = contact.address)
                Text(text = contact.email)
            }
        }
    }

}
