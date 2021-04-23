package com.yousef.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yousef.newsapp.models.Article


@Database(entities = arrayOf(Article::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        private const val DATABASE_NAME = "news-db"
        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(applicationContext).also { instance = it }
            }
        }

        private fun buildDatabase(applicationContext: Context): AppDatabase  {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()


        }
    }





}