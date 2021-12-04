package com.spotlightapps.mydog.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotlightapps.mydog.AppRepository
import com.spotlightapps.mydog.data.ApiCallStatus
import com.spotlightapps.mydog.model.breed.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */
@HiltViewModel
class DogListViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private var _breeds = MutableLiveData<List<Breed>?>()
    val breedList: LiveData<List<Breed>?> = _breeds

    private var _apiCallStatus = MutableLiveData<ApiCallStatus>()
    val apiCallStatus: LiveData<ApiCallStatus> = _apiCallStatus

    fun getBreedList() {
        viewModelScope.launch {
            _apiCallStatus.value = ApiCallStatus.PROGRESS
            try {
                val breedListResponse = appRepository.getBreedList().await()
                _breeds.value = breedListResponse
                _apiCallStatus.value = ApiCallStatus.SUCCESS
            } catch (e: Exception) {
                e.printStackTrace()
                _apiCallStatus.value = ApiCallStatus.FAILED
            }
        }
    }
}