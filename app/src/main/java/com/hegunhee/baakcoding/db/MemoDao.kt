package com.hegunhee.baakcoding.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemoDao {

    @Query("SELECT * FROM Memo")
    fun getAll(): List<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo)

    @Query("SELECT * FROM Memo where primary_key =:id")
    fun getById(id : Int)

}