package com.spotlightapps.mydog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotlightapps.mydog.AppRepository
import com.spotlightapps.mydog.util.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


data class DogListUiState(
    val isFetchingData: Boolean = true
)

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private var _dogImage = MutableStateFlow(emptyList<String?>())
    var dogImage = _dogImage.asStateFlow()

    private var _uiState = MutableStateFlow(DogListUiState(isFetchingData = true))
    val uiState: StateFlow<DogListUiState> = _uiState.asStateFlow()

    private var breedsIdList = emptyList<Int?>()

    val breeds = appRepository.getBreedList()
        .flowOn(Dispatchers.IO)
        .onEach { bread -> breedsIdList = bread!!.map { it.id } }
        .onCompletion { _uiState.value = DogListUiState(isFetchingData = false) }
        .map { value -> value?.map { it.name } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getDogsImageList(position: Int) {
        _uiState.update {
            it.copy(isFetchingData = true)
        }
        viewModelScope.launch {
            appRepository.getDogImageList(breedsIdList[position]!!, true)
                .catch { e -> e.printStackTrace() }
                .onCompletion { _uiState.update { it.copy(isFetchingData = false) } }
                .collect {
                    _dogImage.value = it.map { it.url }
                }
        }
    }
}