/*
 * Copyright (c) 2021. All rights reserved by Innoctive Technologies
 * Created by Shahbaz
 * Last Modified at 12/10/21, 4:07 AM
 */

package com.shazdroid.spacexapp.utility

sealed class ResultData<out T> {
    data class Loading(val nothing: Nothing? = null) : ResultData<Nothing>()
    data class Success<out T>(val data: T? = null) : ResultData<T>()
    data class Exception(val message: String? = null) : ResultData<Nothing>()
}