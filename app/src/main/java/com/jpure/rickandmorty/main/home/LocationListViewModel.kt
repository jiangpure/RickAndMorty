package com.jpure.rickandmorty.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * @author Jp
 * @date 2021/3/30.
 */
@FlowPreview
class LocationListViewModel @ViewModelInject constructor(private val repository: RickAndMortyRepository):ViewModel() {

    fun getLocationList(): Flow<PagingData<Locations>> = repository.fetchLocationsList().cachedIn(viewModelScope)
}