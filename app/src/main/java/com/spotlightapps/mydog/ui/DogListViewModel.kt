package com.spotlightapps.mydog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotlightapps.mydog.DogRepository
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
open class DogListViewModel @Inject constructor(
    private val defaultDogRepository: DogRepository
) : ViewModel() {

    private var _dogImage = MutableStateFlow(emptyList<String?>())
    var dogImage = _dogImage.asStateFlow()

    private var _uiState = MutableStateFlow(DogListUiState(isFetchingData = true))
    val uiState: StateFlow<DogListUiState> = _uiState.asStateFlow()

    private var breedsIdList = emptyList<Int?>()

    val breedNames = flow {
        val breeds = defaultDogRepository.getBreedList(false)
        breedsIdList = breeds?.map { it.id }!!
        _uiState.value = DogListUiState(isFetchingData = false)
        emit(breeds.map { it.name })
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getDogsImageList(position: Int) {
        _uiState.update {
            it.copy(isFetchingData = true)
        }
        viewModelScope.launch {
            val dogImages = defaultDogRepository.getDogImageList(
                if (breedsIdList.isEmpty()) 0 else breedsIdList[position]!!,
                true
            )
            _uiState.update { it.copy(isFetchingData = false) }
            _dogImage.value = dogImages.map { it?.url }
        }
    }
}