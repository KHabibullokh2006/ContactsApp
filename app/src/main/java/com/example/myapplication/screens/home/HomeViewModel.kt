package com.example.myapplication.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.database.ContactEntity

private const val TAG = "HomeViewModel"
class HomeViewModel(private val navController: NavController, private val homeModel: HomeModel) : ViewModel(){
    private var _list: MutableLiveData<List<ContactEntity>> = MutableLiveData(listOf())
    var list: LiveData<List<ContactEntity>> = _list

    fun loadList() {
        _list.value = homeModel.getAllContacts()
    }

    fun onAdd() {
        val id = -1
        navController.navigate("AddView/$id")
    }

    fun delete(contactEntity: ContactEntity) {
        homeModel.onDelete(contactEntity)
        loadList()
    }

    fun onUpdate(contactEntity: ContactEntity) {
        navController.navigate("AddView/${contactEntity.id}")
    }

    fun sortAZ() {
        // Get the current list from the MutableLiveData
        val currentList = _list.value ?: emptyList()

        // Sort the list alphabetically by name
        val sortedList = currentList.toMutableList().apply {
            sortBy { it.name }
            Log.d(TAG, "sortAZ")
        }

        // Update the MutableLiveData with the sorted list
        _list.value = sortedList
    }

    fun sortZA() {
        // Get the current list from the MutableLiveData
        val currentList = _list.value ?: emptyList()

        // Sort the list alphabetically by name
        val sortedList = currentList.toMutableList().apply {
            sortByDescending { it.name }
            Log.d(TAG, "sortZA")
        }

        // Update the MutableLiveData with the sorted list
        _list.value = sortedList
    }
}