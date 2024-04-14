package com.example.myapplication.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.database.ContactEntity

@Composable
fun ContactItem(contact: ContactEntity, onUpdate: (id:Int) -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(2.dp))
        Card {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "", Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = contact.name!!,
                        fontWeight = FontWeight.W500,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                    Text(
                        text = contact.phone!!.toString(),
                        color = Color(0xFF1a73e8),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }

            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(onClick = { onUpdate(contact.id) }) {
                    Icon(Icons.Rounded.Edit, contentDescription = "", tint = Gray)
                }

            }

        }
    }
}