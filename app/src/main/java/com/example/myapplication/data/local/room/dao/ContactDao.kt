package com.example.myapplication.data.local.room.dao

import androidx.room.*
import com.example.myapplication.data.local.room.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactEntity: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactEntity: List<ContactEntity>)

    @Delete
    suspend fun delete(contactEntity: ContactEntity)

    @Update
    suspend fun update(contactEntity: ContactEntity)

//    @Query("SELECT * FROM contact where status != -1")
//    fun getAllContacts():Flow<List<ContactEntity>>
//
//    @Query("SELECT * FROM contact where status != 0")
//    suspend fun modifiedContacts(): List<ContactEntity>

//    @Query("DELETE FROM contact")
//    suspend fun clearContacts()
//
//    @Transaction
//    suspend fun sharedByRemote(list: List<ContactEntity>){
//        clearContacts()
//        insert(list)
//    }


}