package com.example.myapplication.utils
//
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Share
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.myapplication.utils.SearchState
//import com.example.myapplication.utils.IconState
//import com.example.myapplication.screens.home.HomeViewModel
//
//@Composable
//fun CustomTopAppBar(
//    sharedViewModel: HomeViewModel,
//    searchAppBarState: SearchState,
//    searchTextState: String
//) {
//    when (searchAppBarState) {
//        SearchState.CLOSED -> {
//            DefaultTopAppBar(
//                onSearchClicked = {
//                    sharedViewModel.searchState.value =
//                        SearchState.OPENED
//                }
//            )
//        }
//        else -> {
//            SearchTopAppBar(
//                text = searchTextState,
//                onTextChange = { text ->
//                    sharedViewModel.searchTextState.value = text
//                },
//                onCloseClicked = {
//                    sharedViewModel.searchState.value =
//                        SearchState.CLOSED
//                    sharedViewModel.searchTextState.value = ""
//                },
//                onSearchClicked = {}
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DefaultTopAppBar(
//    onSearchClicked: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .height(80.dp)
//            .fillMaxWidth()
//    ) {
////        Image(
////            modifier = Modifier.fillMaxSize(),
////            painter = painterResource(id = R.drawable.ic_top_app_bar_bg),
////            contentDescription = "background_image",
////            contentScale = ContentScale.FillBounds
////        )
//        TopAppBar(
//            modifier = Modifier.padding(top = 24.dp),
//            title = {
//
//            },
//            actions = {
//                AppBarActions(
//                    onSearchClicked = onSearchClicked
//                )
//            }
//        )
//    }
//}
//
//@Composable
//fun AppBarActions(
//    onSearchClicked: () -> Unit
//) {
//    SearchAction(onSearchClicked = onSearchClicked)
//}
//
//@Composable
//fun SearchAction(
//    onSearchClicked: () -> Unit
//) {
//    val context = LocalContext.current
//    IconButton(
//        onClick = {
//            onSearchClicked()
//        }
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = "search_icon",
//            tint = Color.White
//        )
//    }
//}
//
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchTopAppBar(
//    text: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit
//) {
//
//    var trailingIconState by remember {
//        mutableStateOf(IconState.DELETE)
//    }
//
//    Box(
//        modifier = Modifier
//            .height(80.dp)
//            .fillMaxWidth()
//    ) {
////        Image(
////            modifier = Modifier.fillMaxSize(),
////            painter = painterResource(id = R.drawable.ic_top_app_bar_bg),
////            contentDescription = "background_image",
////            contentScale = ContentScale.FillBounds
////        )
//
//        Surface(
//            modifier = Modifier
//                .padding(top = 24.dp)
//                .fillMaxSize(),
//            color = Color.Transparent,
//
//        ) {
//            TextField(
//                modifier = Modifier.fillMaxSize(),
//                value = text,
//                onValueChange = {
//                    onTextChange(it)
//                },
//                textStyle = TextStyle(
//                    color = Color.White,
//                    fontSize = 16.sp
//                ),
//                singleLine = true,
//                leadingIcon = {
//                    IconButton(
//                        modifier = Modifier
//                            .alpha(ContentAlpha.medium),
//                        onClick = {}
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            contentDescription = "",
//                            tint = Color.White
//                        )
//                    }
//                },
//                trailingIcon = {
//                    IconButton(onClick = {
//                        when (trailingIconState) {
//                            IconState.DELETE -> {
//                                onTextChange("")
//                                trailingIconState = IconState.CLOSE
//                            }
//                            IconState.CLOSE -> {
//                                if (text.isNotEmpty()) {
//                                    onTextChange("")
//                                } else {
//                                    onCloseClicked()
//                                    trailingIconState = IconState.DELETE
//                                }
//                            }
//                        }
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.Close,
//                            contentDescription = "",
//                            tint = Color.White
//                        )
//                    }
//                },
//                keyboardOptions = KeyboardOptions(
//                    imeAction = ImeAction.Search
//                ),
//                keyboardActions = KeyboardActions(
//                    onSearch = {
//                        onSearchClicked(text)
//                    }
//                ),
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.White,
//                    focusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//
//                )
//            )
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun CustomAppBarPreview() {
//    DefaultTopAppBar(
//        onSearchClicked = {}
//    )
//}
//
//@Preview
//@Composable
//fun SearchAppBarPreview() {
//    SearchTopAppBar(
//        text = "",
//        onTextChange = {},
//        onCloseClicked = { },
//        onSearchClicked = {}
//    )
//}