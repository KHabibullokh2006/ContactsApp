package com.example.myapplication.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
    val phone: String? = null,
//    val photo: String? = null
)

@Dao
interface ContactDao{
    @Query("select * from ContactEntity where id = :contactId")
    fun getContact(contactId:Int):ContactEntity

    @Insert
    fun addContact(contact: ContactEntity)

    @Update
    fun updateContact(contact: ContactEntity)

    @Delete
    fun deleteContact(contact: ContactEntity)

    @Query("select * from ContactEntity")
    fun getAllContacts(): List<ContactEntity>

    @Query("select * from ContactEntity order by name asc")
    fun getAscContacts():List<ContactEntity>

    @Query("select * from ContactEntity order by name desc")
    fun getDescContacts():List<ContactEntity>
}

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDao(): ContactDao

    companion object{
        private var instance : AppDataBase? = null
        fun getInstance(context: Context): AppDataBase{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "app_dp").allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}
