package com.example.myapplication.screens.home

import android.util.Log

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.database.ContactEntity
import com.example.myapplication.utils.SearchState

private const val TAG = "HomeViewModel"

class HomeViewModel(private val navController: NavController, private val homeModel: HomeModel) : ViewModel() {
    private var _list: MutableLiveData<List<ContactEntity>> = MutableLiveData(listOf())
    var list: LiveData<List<ContactEntity>> = _list

    val searchState: MutableState<SearchState> =
        mutableStateOf(SearchState.CLOSED)

    val searchTextState: MutableState<String> =
        mutableStateOf("")

    fun loadList() {
        _list.value = homeModel.getAllContacts()
        Log.d(TAG, "loadList ${_list.value}")
    }

    fun onAdd() {
        val id = -1
        navController.navigate("AddView/$id")
        Log.i(TAG, "onAdd")
    }


    fun onRemove(contactEntity: ContactEntity) {
        homeModel.onDelete(contactEntity)
        Log.i(TAG, "onRemove")
        loadList()
    }

    fun onUpdate(contactEntity: ContactEntity) {
        navController.navigate("AddView/${contactEntity.id}")
    }

    fun sortAZ() {
        _list.value = homeModel.getAZContacts()
        loadList()
    }

    fun sortZA() {
        _list.value = homeModel.getZAContacts()
        loadList()
    }

    fun search() {

    }
}