package com.jpure.rickandmorty.main.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.repository.ListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

/**
 * @author Jp
 * @date 2021/2/25.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(private val repository: ListRepository):ViewModel() {

    fun loadRoleList(): LiveData<PagingData<Role>> =
        repository.fetchRoleList().cachedIn(viewModelScope).asLiveData()
}