package com.example.myapplication.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.myapplication.ui.theme.Primary
import com.example.myapplication.ui.theme.Primary2
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm : HomeViewModel) {
    vm.loadList()
    val list = vm.list.observeAsState().value
    var displayMenu by remember { mutableStateOf(false) }

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
                val context = LocalContext.current
                var show by remember { mutableStateOf(true) }
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            show = false
                            true
                        } else false
                    }, positionalThreshold = { 150.dp.toPx() }
                )
                AnimatedVisibility(
                    show,exit = fadeOut(spring())
                ) {
                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        background = {
                            DismissBackground(dismissState)
                        },
                        dismissContent = {
                            ContactItem(
                                contact = list[index],
                                onUpdate = {vm.onUpdate(list[index])}
                            )
                        }
                    )
                }

                LaunchedEffect(show) {
                    if (!show) {
                        delay(800)
                        vm.onRemove(list[index])
                        show=true
                        Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
                    }
                }


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