package com.hegunhee.baakcoding.model

import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.db.MemoDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val dao : MemoDao) : Repository {
    override suspend fun getAll(): List<Memo> {
        return dao.getAll()
    }

    override suspend fun insert(memo: Memo) {
        dao.insert(memo)
    }

    override suspend fun getByPrimaryKey(id: Int): Memo {
        return dao.getByPrimaryKey(id)
    }

    override suspend fun delete(memo: Memo) {
        dao.delete(memo)
    }
}