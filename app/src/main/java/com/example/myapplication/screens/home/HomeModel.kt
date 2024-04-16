package com.example.myapplication.screens.home

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.myapplication.database.ContactDao
import com.example.myapplication.database.ContactEntity

class HomeModel(private val appDao: ContactDao){
    fun getAllContacts():List<ContactEntity>{
        return appDao.getAllContacts()
    }

    fun onDelete(contactEntity: ContactEntity){
        appDao.deleteContact(contactEntity)
    }
    fun getAZContacts():List<ContactEntity>{
        return appDao.getAscContacts()
    }
    fun getZAContacts():List<ContactEntity>{
        return appDao.getDescContacts()
    }
}