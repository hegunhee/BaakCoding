package com.hegunhee.baakcoding.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.model.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    val memoList = MutableLiveData<List<Memo>>(listOf())
    var list = listOf<Memo>()
    var query : String = ""


    fun initList() = viewModelScope.launch{
        list = repository.getAll()
        memoList.postValue(list)
        repository.getAll().run {
            Log.d("TestItems",this.toString())
        }
    }

    private fun setList() = viewModelScope.launch{
        list = if(query == ""){
            repository.getAll()
        }else{
            repository.getAll().filter { it.data.contains(query) }.toList()
        }
        memoList.postValue(list)
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            query = ""
            setList()
        } else {
            query = s.toString()
            setList()
        }
    }

    fun deleteMemo(memo : Memo) = viewModelScope.launch {
        repository.delete(memo)
        setList()
    }

}