package com.jpure.rickandmorty.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Pure Jiang
 * @date 2021/5/12.
 */
class ListViewModel @ViewModelInject constructor(private val repository: RickAndMortyRepository): ViewModel() {
    fun getLocationList(): Flow<PagingData<Locations>> = repository.fetchLocationsList().cachedIn(viewModelScope)

}