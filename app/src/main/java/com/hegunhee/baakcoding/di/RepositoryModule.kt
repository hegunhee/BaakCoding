//package com.hegunhee.baakcoding.di
//
//import com.hegunhee.baakcoding.db.MemoDao
//import com.hegunhee.baakcoding.model.DefaultRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//object RepositoryModule {
//
//    @Singleton
//    @Provides
//    fun provideDefaultRepository(dao : MemoDao) : DefaultRepository{
//        return DefaultRepository(dao)
//    }
//}