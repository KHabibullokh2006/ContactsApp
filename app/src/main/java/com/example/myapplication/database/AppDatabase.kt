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
    @Query("select * from ContactEntity where id = :expenseId")
    fun getContact(expenseId:Int):ContactEntity

    @Insert
    fun addContact(expense: ContactEntity)

    @Update
    fun updateContact(expense: ContactEntity)

    @Delete
    fun deleteContact(expense: ContactEntity)

    @Query("select * from ContactEntity")
    fun getAllContacts(): List<ContactEntity>
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
