package com.m.edamam.database

import android.arch.persistence.room.*
import com.m.edamam.pojo.Hit

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hit: Hit)

    @Update
    fun update(hit: Hit)


    @Delete
    fun delete(hit: Hit)

    @Query("select * from recipes")
    fun getAllAsList(): List<Hit>

    @Query("delete from recipes")
    fun clear()
}