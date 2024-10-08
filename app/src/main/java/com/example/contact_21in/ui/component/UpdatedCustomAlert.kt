import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.contact_21in.isEmailValid
import com.example.contact_21in.isNameValid
import com.example.contact_21in.isPhoneValid
import com.example.contact_21in.ui.component.Input
import com.example.contact_21in.ui.model.Contact

@Composable
fun UpdateCustomAlert(
    visibleUpdateAlert: MutableState<Boolean>,
    indexOf: MutableState<Int>,
    contact: Contact,
    contacts: SnapshotStateList<Contact>,
    modifier: Modifier = Modifier
) {

    var nameState = remember { mutableStateOf(contact.name) }
    var phoneState = remember { mutableStateOf(contact.phone) }
    var addressState = remember { mutableStateOf(contact.address) }
    var emailState = remember { mutableStateOf(contact.email) }

    var errorName = remember { mutableStateOf(true) }
    var errorPhone = remember { mutableStateOf(true) }
    var errorAddress = remember { mutableStateOf(false) }
    var errorEmail = remember { mutableStateOf(true) }

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
            Text("update contact?")
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

                    visibleUpdateAlert.value = false;

                }) { Text("Cancel") }

                Button(onClick = {
                    Log.i("Alert", "${indexOf.value}")
                    contacts[indexOf.value] = Contact(
                        nameState.value,
                        phoneState.value,
                        addressState.value,
                        emailState.value
                    )


                    visibleUpdateAlert.value = false
//                    }

                })
                { Text("Confirm") }

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        contacts.removeAt(indexOf.value)
                        indexOf.value = -1
                        visibleUpdateAlert.value = false

                    })
//
            }
        }

    }
}