package com.shazdroid.spacexapp.repository

import com.shazdroid.spacexapp.network.ApiInterface
import com.shazdroid.spacexapp.room.RocketDao
import com.shazdroid.spacexapp.utility.ResultData
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val apiInterface: ApiInterface, private val rocketDao: RocketDao
) {
    /**
     * Get rocket list
     * from network and cache it, if device is offline i.e no internet then
     * get cached data from room db
     */
    suspend fun getRocketList(): ResultData<List<RocketListResponseItem>> {
        return try {
            val result = apiInterface.getRocketList()
            if (result != null) {
                rocketDao.insertRockets(result)
                ResultData.Success(result)
            } else {
                ResultData.Success(rocketDao.getAllRockets())
            }
        } catch (e: Exception) {
            val result = rocketDao.getAllRockets()
            if (result != null && result.isNotEmpty()) {
                ResultData.Success(rocketDao.getAllRockets())
            } else {
                ResultData.Exception("No internet connection available")
            }
        }
    }

    /**
     * Get rocket detail from room db
     * if it is not present in room db then fetch it from API
     */
    suspend fun getRocketDetail(id: String): ResultData<RocketListResponseItem> {
        return try {
            var result = rocketDao.getRocketById(id)
            if (result != null) {
                ResultData.Success(result)
            } else {
                result = apiInterface.getRocketDetail(id)
                rocketDao.insertRocket(result)
                ResultData.Success(result)
            }
        } catch (e: Exception) {
            ResultData.Success(rocketDao.getRocketById(id))
        }
    }

}