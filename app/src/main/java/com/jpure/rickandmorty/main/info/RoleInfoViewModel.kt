package com.jpure.rickandmorty.main.info

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.repository.RickAndMortyRepository
import com.jpure.rickandmorty.ext.doFailure
import com.jpure.rickandmorty.ext.doSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * @author Jp
 * @date 2021/3/10.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class RoleInfoViewModel @ViewModelInject constructor(val repository: RickAndMortyRepository) :
    ViewModel() {

     val loading = ObservableBoolean()

    // 私有的 MutableLiveData 可变的，对内访问
    private val _role = MutableLiveData<Role>()

    // 对外暴露不可变的 LiveData，只能查询
    val role: LiveData<Role> = _role

    private val _failure = MutableLiveData<String>()
    val failure = _failure

    fun loadRoleInfo(id: Int) = liveData<Role> {
        repository.fetchRoleInfo(id).onStart {
            // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
            loading.set(true)
        }.catch {
            // 捕获上游出现的异常
            loading.set(false)
        }.onCompletion {
            // 请求完成
            loading.set(false)
        }.collectLatest { result ->
            result.doFailure { throwable ->
                _failure.value = throwable?.message ?: "failure"
            }
            result.doSuccess { value ->
                _role.postValue(value)
                emit(value)
            }
        }
    }
}