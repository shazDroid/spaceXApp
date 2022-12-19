package com.shazdroid.spacexapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shazdroid.spacexapp.utility.Converters
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem

@Database(entities = [RocketListResponseItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
        }
    }

    abstract fun getRocketDao(): RocketDao
}