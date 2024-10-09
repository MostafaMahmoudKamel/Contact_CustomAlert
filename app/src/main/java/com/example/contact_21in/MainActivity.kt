package com.example.contact_21in

import UpdateCustomAlert
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contact_21in.ui.component.CustomAlert
import com.example.contact_21in.ui.component.DisplayContactOnScreen
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
    //state variable to control add/update
    var visibleCustomAlert = remember { mutableStateOf(false) }
    var visibleCustomAlertUpdated = remember { mutableStateOf(false) }
    var selectedContactIndex = remember { mutableStateOf(-1) }
    var contacts = remember { mutableStateListOf<Contact>() }//allcontact


    Box(
        Modifier
            .padding(32.dp)
            .fillMaxSize()
    ) {
        //title
        Text(
            "Contact List Application",
            fontWeight = FontWeight.Bold
        )

        // Display contacts or a message if the list is empty
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            if (contacts.isEmpty()) {
                Text("please click +", Modifier.padding(32.dp))
            } else {
                contacts.forEachIndexed { index, contact ->
                    DisplayContactOnScreen(contact = contact) {
                        // Show update alert when a contact is clicked
                        visibleCustomAlertUpdated.value = true
                        selectedContactIndex.value = index
                    }
                }
            }
        }

        //add new contact
        FloatingActionButton(
            onClick = { visibleCustomAlert.value = true },//show add contact dailog
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

          // CustomAlert for adding a new contact
        CustomAlert(
            visibleCustomAlert,
            contacts = contacts,
            modifier = Modifier.align(Alignment.Center),
            onCancel = { visibleCustomAlert.value = false; }
        )

        if (visibleCustomAlertUpdated.value) {
            UpdateCustomAlert(
                visibleUpdateAlert = visibleCustomAlertUpdated,
                contact = contacts[selectedContactIndex.value],
                indexOf = selectedContactIndex,
                contacts = contacts,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}








