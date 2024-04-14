package com.example.myapplication.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Primary
import com.example.myapplication.ui.theme.Primary2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm : HomeViewModel) {
    vm.loadList()
    val list = vm.list.observeAsState().value
    var displayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Contacts") },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                }
                IconButton(onClick = { displayMenu = !displayMenu }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "menu")
                }
                DropdownMenu(expanded = displayMenu, onDismissRequest = { displayMenu = false }) {
                    DropdownMenuItem(text = { Text(text = "A-Z") }, onClick = { vm.sortAZ() })
                    DropdownMenuItem(text = { Text(text = "Z-A") }, onClick = { vm.sortZA() })
                }
            })

        LazyColumn (Modifier.padding(horizontal = 6.dp)){
            items(list!!.size) {index->
                ContactItem(
                    contact = list[index],
                    onUpdate = {vm.onUpdate(list[index])}
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Box (
            Modifier
                .padding(6.dp)
                .align(Alignment.BottomEnd)){
            FloatingActionButton(onClick = {vm.onAdd() }, containerColor = Color(0xFF1a73e8)) {
                Icon(Icons.Rounded.Add, contentDescription = "", tint = Color.White)
            }
        }
    }
}