package com.example.myapplication.screens.addEdit

import com.example.myapplication.database.ContactDao
import com.example.myapplication.database.ContactEntity

class AddEditModel(private val appDao :ContactDao) {
    fun getContact(id:Int): ContactEntity {
        return appDao.getContact(id)
    }

    fun addContact(contact: ContactEntity){
        appDao.addContact(contact)
    }

    fun update(contact: ContactEntity) {
        appDao.updateContact(contact)
    }
}