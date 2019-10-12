package com.example.jossemarapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jossemarapp.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase: RoomDatabase() {

    abstract fun postDao(): PostInterface

    companion object {
        var INSTANCE: PostDatabase? = null

        fun getAppDataBase(context: Context): PostDatabase? {
            if (INSTANCE == null){
                synchronized(PostDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, "postDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}