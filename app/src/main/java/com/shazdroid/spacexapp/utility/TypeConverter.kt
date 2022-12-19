package com.shazdroid.spacexapp.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem


class Converters {
    @TypeConverter
    fun listToJson(value: List<String?>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun toDiameter(value: RocketListResponseItem.Diameter): String = Gson().toJson(value)

    @TypeConverter
    fun fromDiameter(value: String): RocketListResponseItem.Diameter = Gson().fromJson(value, RocketListResponseItem.Diameter::class.java)

    @TypeConverter
    fun toEngines(value: RocketListResponseItem.Engines): String = Gson().toJson(value)

    @TypeConverter
    fun fromEngines(value: String): RocketListResponseItem.Engines = Gson().fromJson(value, RocketListResponseItem.Engines::class.java)

    @TypeConverter
    fun toHeight(value: RocketListResponseItem.Height): String = Gson().toJson(value)

    @TypeConverter
    fun fromHeight(value: String): RocketListResponseItem.Height = Gson().fromJson(value, RocketListResponseItem.Height::class.java)
}