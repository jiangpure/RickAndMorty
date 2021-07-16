package com.jpure.rickandmorty.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * @author Pure Jiang
 * @date 2021/2/25.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class RoleListViewModel @ViewModelInject constructor(private val repository: RickAndMortyRepository):ViewModel() {

    fun loadRoleList(): Flow<PagingData<Role>> =
        repository.fetchRoleList().cachedIn(viewModelScope)
}