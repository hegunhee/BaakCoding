package com.hegunhee.baakcoding.model

import com.hegunhee.baakcoding.db.Memo

interface Repository {

    suspend fun getAll() : List<Memo>

    suspend fun insert(memo : Memo)

    suspend fun getByPrimaryKey(id : Int) : Memo

    suspend fun delete(memo : Memo)
}