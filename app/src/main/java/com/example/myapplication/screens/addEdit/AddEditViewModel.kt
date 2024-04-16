package com.example.myapplication.screens.addEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.myapplication.database.ContactEntity

class AddEditViewModel(private val navController: NavController, val id: Int, private val model: AddEditModel) {
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> = _phone

    var contact = ContactEntity()



    fun onBack() {
        navController.popBackStack()
    }

    init {
        if (id != -1) loadContact()
    }

    private fun loadContact() {
        val con = model.getContact(id)
        _name.value = con.name
        _phone.value = con.phone
        contact = con
    }

    fun onAddUpdate(contact: ContactEntity) {
        if (id ==-1){
            model.addContact(contact)
            navController.popBackStack()
        }else{
            updateContact()
            navController.popBackStack()
        }

    }

    private fun updateContact() {
        val newName = name.value
        val newPhone = phone.value
        val contact = ContactEntity(contact.id, newName, newPhone)
        model.update(contact)
    }

    fun updateName(name: String) {
        _name.value = name
    }

    fun updatePhone(phone: String) {
        _phone.value = phone
    }

    fun getTopBarText(): String {
        return if (id == -1) "New Contact" else "Edit Contact"
    }

    fun getButtonText(): String {
        return if (id == -1) "Add" else "Save"
    }
}