package com.spotlightapps.mydog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotlightapps.mydog.DogRepository
import com.spotlightapps.mydog.Result
import com.spotlightapps.mydog.domain.LoadBreedListUseCase
import com.spotlightapps.mydog.succeeded
import com.spotlightapps.mydog.util.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmad Jawid Muhammadi
 * on 04-12-2021.
 */


data class DogListUiState(
    val isFetchingData: Boolean = true,
    val errorMessage: String? = null
)

@HiltViewModel
open class DogListViewModel @Inject constructor(
    private val defaultDogRepository: DogRepository,
    private val loadBreedListUseCase: LoadBreedListUseCase
) : ViewModel() {

    private var _dogImage = MutableStateFlow(emptyList<String?>())
    var dogImage = _dogImage.asStateFlow()

    private var _uiState = MutableStateFlow(DogListUiState(isFetchingData = true))
    val uiState: StateFlow<DogListUiState> = _uiState.asStateFlow()

    private var breedsIdList = emptyList<Int?>()

    val breedNames = flow {
        val breeds = loadBreedListUseCase(true)
        _uiState.value = DogListUiState(isFetchingData = false)
        if (breeds.succeeded) {
            breedsIdList = (breeds as Result.Success).data?.map { it.id }!!
            emit(breeds.data?.map { it.name })
        } else {
            _uiState.update { it.copy(errorMessage = (breeds as Result.Error).exception.message) }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getDogsImageList(position: Int) {
        _uiState.update {
            it.copy(isFetchingData = true)
        }
        viewModelScope.launch {
            val dogImageResult = defaultDogRepository.getDogImageList(
                if (breedsIdList.isEmpty()) 0 else breedsIdList[position]!!,
                true
            )
            _uiState.update { it.copy(isFetchingData = false) }
            if (dogImageResult.succeeded) {
                _dogImage.value = (dogImageResult as Result.Success).data.map { it?.url }
            } else {
                _uiState.update { it.copy(errorMessage = (dogImageResult as Result.Error).exception.message) }
            }
        }
    }
}