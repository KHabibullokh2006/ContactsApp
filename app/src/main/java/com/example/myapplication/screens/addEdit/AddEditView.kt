package com.example.myapplication.screens.addEdit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.database.ContactEntity
import com.example.myapplication.ui.theme.Primary
import com.example.myapplication.ui.theme.Primary2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(vm: AddEditViewModel) {
    val name = vm.name.observeAsState().value!!
    val phone = vm.phone.observeAsState().value!!

    BackHandler {
        vm.onBack()
    }
    Column(
        Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        CenterAlignedTopAppBar(title = { Text(text = vm.getTopBarText()) }, navigationIcon = {
            IconButton(onClick = { vm.onBack() }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "")
            }
        })
        Column(Modifier.padding(horizontal = 12.dp)) {
            OutlinedTextField(
                value = name,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Primary,
                    unfocusedIndicatorColor = Gray,
                    cursorColor = Primary
                ),
                onValueChange = { vm.updateName(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = { Text(text = "Name", color = Gray) },

            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = phone,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Primary,
                    cursorColor = Primary
                ),
                onValueChange = { vm.updatePhone(it) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                placeholder = { Text(text = "Phone", color = Gray) },

            )
        }
        TextButton(
            enabled =
            if (vm.id == -1){
                phone.isNotBlank() && name.isNotBlank()
            }else {
                vm.contact.phone != phone || vm.contact.name != name },
            onClick = { vm.onAddUpdate(ContactEntity(name = name, phone = phone)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Primary,
                disabledContainerColor = Primary2
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = vm.getButtonText(), color = Color.White)
        }
    }
}