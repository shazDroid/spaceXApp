package com.shazdroid.spacexapp.viewmodels

import androidx.lifecycle.*
import com.shazdroid.spacexapp.repository.RocketRepository
import com.shazdroid.spacexapp.utility.ResultData
import com.shazdroid.spacexapp.view.fragments.rocket_list.model.RocketListResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(private val repository: RocketRepository) : ViewModel() {
    val onItemClickEvent = MutableSharedFlow<String>()
    val onImageSelectEvent = MutableSharedFlow<String>()
    val isLoading = MutableLiveData<Boolean>(true)
    val isException = MutableLiveData<Boolean>(false)

    fun getRocketList() : LiveData<ResultData<List<RocketListResponseItem>>> {
        return liveData {
            try {
                emit(ResultData.Loading())
                emit(repository.getRocketList())
            } catch (e : Exception) {
                emit(ResultData.Exception(e.localizedMessage))
            }
        }
    }

    fun getRocketDetail(id: String) : LiveData<ResultData<RocketListResponseItem>> {
        return liveData {
            try {
                emit(ResultData.Loading())
                emit(repository.getRocketDetail(id))
            } catch (e: Exception) {
                emit(ResultData.Exception(e.localizedMessage))
            }
        }
    }

    fun onItemClick(id: String){
        viewModelScope.launch {
            onItemClickEvent.emit(id)
        }
    }

    fun onImageSelect(url: String) {
        viewModelScope.launch {
            onImageSelectEvent.emit(url)
        }
    }

    fun formatCurrency(value: Int) : String {
        return NumberFormat.getCurrencyInstance(Locale("en", "US")).format(value)
    }

}