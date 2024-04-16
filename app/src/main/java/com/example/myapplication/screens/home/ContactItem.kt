package com.example.myapplication.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss

import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.myapplication.database.ContactEntity
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        DismissDirection.StartToEnd -> Color(0xFF1DE9B6)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(5.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.EndToStart) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "delete"
            )
        }
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.StartToEnd) {
            Icon(
                Icons.Default.Call,
                contentDescription = "call"
            )
        }
    }
}

@Composable
fun ContactItem(contact: ContactEntity, onUpdate: (id: Int) -> Unit) {


    Column {
        Spacer(modifier = Modifier.height(2.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = ""
                )
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


@Preview
@Composable
fun ContactItemPreview() {
    ContactItem(contact = ContactEntity(name = "Ali", phone = "+998997906118"), onUpdate = {})
}