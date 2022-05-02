package com.hegunhee.baakcoding.ui.delete

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.model.DefaultRepository
import com.hegunhee.baakcoding.ui.add.AddState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {

    private lateinit var memo : Memo

    val detailState = MutableLiveData<DetailState>(DetailState.Uninitalized)
    fun initViewModel(memo : Memo) {
        this.memo = memo
        _memoTextLength.value = "글자수 : ${memo.data.length.toString()}"
        if(memo.secret){
            passwordText.value = memo.secretPassWord
        }
        memoText = memo.data
    }

    private var _addButtonVisible = MutableLiveData<Boolean>(true)
    val addButtonVisible: LiveData<Boolean>
        get() = _addButtonVisible

    private var _memoTextLength = MutableLiveData<String>()
    val memoTextLength: LiveData<String>
        get() = _memoTextLength

    var passwordText = MutableLiveData<String>("")

    var memoText: String = ""


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            _addButtonVisible.value = false
            _memoTextLength.value = "글자수 : 0"
            memoText = ""
        } else {
            _addButtonVisible.value = true
            memoText = s.toString()
            _memoTextLength.value = "글자수 : ${s.length}"
        }
    }

    fun onClickButton()  = viewModelScope.launch{
        val time: String = LocalDateTime.now().plusHours(9)
            .run { this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) }
        val secret = passwordText.value?.run {
            this != ""
        } ?: kotlin.run {
            false
        }
        val password = if(secret){
            passwordText.value ?: 0
        }else{
            0
        }
        memo.copy(data = memoText,secret = secret,secretPassWord =  password.toString()).run {
            repository.insert(this)
            detailState.postValue(DetailState.Detail)
        }

    }
}