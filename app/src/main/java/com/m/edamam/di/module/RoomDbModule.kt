package com.m.edamam.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.m.edamam.database.RecipeDb
import com.m.edamam.di.module.RoomDbModule.Companion.NAME_DB
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RoomDbModule {

    @Provides
    @Named(NAME_DB)
    @Singleton
    fun provideDbName(): String = "mDatabse.db"

    @Provides
    @Singleton
    fun provideDbInstance(context: Context, @Named(NAME_DB) dbName: String): RecipeDb =
            Room.databaseBuilder(context, RecipeDb::class.java, dbName).build()

    companion object {
        private const val NAME_DB = "DatabaseName"
    }
}
