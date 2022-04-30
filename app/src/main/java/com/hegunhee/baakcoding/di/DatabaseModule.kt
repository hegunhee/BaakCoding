package com.hegunhee.baakcoding.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.baakcoding.db.MemoDao
import com.hegunhee.baakcoding.db.MemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideLogDatabase(
        @ApplicationContext context : Context
    ) : MemoDatabase = Room.databaseBuilder(context,MemoDatabase::class.java,MemoDatabase.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun provideLogDao(memoDatabase: MemoDatabase) : MemoDao = memoDatabase.memoDao()
}