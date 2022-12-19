package com.shazdroid.spacexapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem

@Dao
interface RocketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRockets(rocketItem: List<RocketListResponseItem>)

    @Query("SELECT * FROM rocket_tbl")
    suspend fun getAllRockets() : List<RocketListResponseItem>?

    @Query("SELECT * FROM rocket_tbl WHERE id = :rocketId")
    suspend fun getRocketById(rocketId: String) : RocketListResponseItem?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRocket(rocketItem: RocketListResponseItem)

}