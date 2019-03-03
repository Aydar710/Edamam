package com.m.edamam.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.m.edamam.pojo.Hit

@Database(entities = arrayOf(Hit::class), version = 1)
abstract class HitDb : RoomDatabase() {

    abstract fun getHitDao(): HitDao

    companion object {
        private val DATABASE_NAME = "mDatabse.db"
        private var dbInstance: HitDb? = null
        fun getInstance(context : Context): HitDb {
            if(dbInstance == null){
                dbInstance = Room.databaseBuilder(context,
                        HitDb::class.java, DATABASE_NAME).build()

            }
            return dbInstance as HitDb
        }
    }
}