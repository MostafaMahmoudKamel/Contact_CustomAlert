package com.example.contact_21in

import UpdateCustomAlert
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contact_21in.ui.component.DisplayContactOnScreen
import com.example.contact_21in.ui.component.Input
import com.example.contact_21in.ui.model.Contact
import com.example.contact_21in.ui.theme.Contact_21inTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Contact_21inTheme {
                ContactScreen()
            }
        }
    }
}

@Composable
fun ContactScreen() {
    AllContent()
}

@Composable
fun AllContent() {

    var visibleCustomAlert = remember { mutableStateOf(false) }
    var visibleCustomAlertUpdated = remember { mutableStateOf(false) }
    var indexOfItem = remember { mutableStateOf(-1) }
    var Contacts = remember { mutableStateListOf<Contact>() }

    Box(
        Modifier
            .padding(32.dp)
            .fillMaxSize()
    ) {

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                "Contact List Application",
//                Modifier.align(Alignment.TopStart),
                fontWeight = FontWeight.Bold
            )
            if (Contacts.isEmpty()) {
                Text("please click +", Modifier.padding(32.dp))
            } else {
                Contacts.forEachIndexed { index, contact ->
                    indexOfItem.value = index
                    DisplayContactOnScreen(
                        visibleCustomAlert,
                        updatevisibleAlert = visibleCustomAlertUpdated,
                        contact = contact
                    )
//                    if(visibleCustomAlertUpdated.value){
//                        UpdateCustomAlert(visibleCustomAlertUpdated, contact = contact, indexOf =indexOfItem , contacts = Contacts,
//                            modifier =  Modifier.align(Alignment.Center)
//                        )
//                    }
                }

            }

        }
        FloatingActionButton(
            onClick = { visibleCustomAlert.value = true },
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)
        ) {
        }

        CustomAlert(
            visibleCustomAlert,
            visibleCustomAlertUpdated,
            Contacts,
            modifier = Modifier.align(Alignment.Center)
        )

        if (visibleCustomAlertUpdated.value) {
            UpdateCustomAlert(
                visibleCustomAlertUpdated,
                contact = Contacts[indexOfItem.value],
                indexOf = indexOfItem,
                contacts = Contacts,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Composable
fun CustomAlert(
    visibleCustomAlert: MutableState<Boolean>,
    updateAlert: MutableState<Boolean>,
    Contacts: SnapshotStateList<Contact>,
    modifier: Modifier = Modifier,

    ) {
    var nameState = remember { mutableStateOf("") }
    var phoneState = remember { mutableStateOf("") }
    var addressState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }

    var errorName = remember { mutableStateOf(true) }
    var errorPhone = remember { mutableStateOf(true) }
    var errorAddress = remember { mutableStateOf(false) }
    var errorEmail = remember { mutableStateOf(true) }

    if (visibleCustomAlert.value) {//visible
        Box(
            modifier = Modifier
                .then(modifier)
                .height(450.dp)
                .width(300.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
//                .align(Alignment.Center) //from parent
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Text("Add contact")
                Input(nameState, errorName, label = "name")
                Input(phoneState, errorPhone, label = "phone")
                Input(addressState, errorAddress, label = "Address")
                Input(emailState, errorEmail, label = "email")

                if ((nameState.value).isNameValid()) {
                    errorName.value = false
                } else {
                    errorName.value = true
                }
                if (emailState.value.isEmailValid()) {
                    errorEmail.value = false
                } else {
                    errorEmail.value = true
                }
                if (phoneState.value.isPhoneValid()) {
                    errorPhone.value = false
                } else {
                    errorPhone.value = true
                }

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Button(onClick = {

                        visibleCustomAlert.value = false;

                    }) { Text("Cancel") }

                    Button(onClick = {

                        if (!errorName.value && !errorEmail.value && !errorPhone.value) {
                            Contacts.add(
                                Contact(
                                    nameState.value,
                                    phoneState.value,
                                    addressState.value,
                                    emailState.value
                                )
                            )
                            nameState.value = ""
                            phoneState.value = ""
                            addressState.value = ""
                            emailState.value = ""
                            visibleCustomAlert.value = false
                        }

                    })
                    { Text("Confirm") }


                }
            }

        }
    }




}







