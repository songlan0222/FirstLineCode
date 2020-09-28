package com.songlan.jetpacktest

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.songlan.jetpacktest.Skill.MyApplication

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var database: AppDatabase? = null
        fun getDatabase(): AppDatabase {
            database?.let {
                return it
            }

            return Room.databaseBuilder(MyApplication.context, AppDatabase::class.java, "app_database").build()
                .apply {
                    database = this
                }
        }
    }

}