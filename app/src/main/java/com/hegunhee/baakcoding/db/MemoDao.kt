package com.hegunhee.baakcoding.db

import androidx.room.*

@Dao
interface MemoDao {

    @Query("SELECT * FROM Memo")
    suspend fun getAll(): List<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memo: Memo)

    @Query("SELECT * FROM Memo where primary_key =:id")
    suspend fun getByPrimaryKey(id : Int) : Memo

    @Delete
    suspend fun delete(memo : Memo)
}