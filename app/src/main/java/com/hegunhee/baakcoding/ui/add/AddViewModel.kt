package com.hegunhee.baakcoding.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.model.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository : DefaultRepository) : ViewModel() {

    private var _addButtonClickable = MutableLiveData<Boolean>(false)
    val addButtonClickable: LiveData<Boolean>
        get() = _addButtonClickable

    private var _memoTextLength = MutableLiveData<String>()
    val memoTextLength: LiveData<String>
        get() = _memoTextLength

    var passwordText = MutableLiveData<String>("")

    var addState = MutableLiveData<AddState>(AddState.Uninitalized)

    var memoText: String = ""


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            _addButtonClickable.value = false
            _memoTextLength.value = "글자수 : 0"
            memoText = ""
        } else {
            _addButtonClickable.value = true
            memoText = s.toString()
            _memoTextLength.value = "글자수 : ${s.length}"
        }
    }

    fun onClickButton()  = viewModelScope.launch{
        val time: String = LocalDateTime.now().plusHours(9).run { this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) }
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
        Memo(memoText,time,secret,password.toString()).run {
            repository.insert(this)
            addState.postValue(AddState.Add)
        }
    }
}