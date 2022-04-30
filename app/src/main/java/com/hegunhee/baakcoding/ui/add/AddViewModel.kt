package com.hegunhee.baakcoding.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hegunhee.baakcoding.db.Memo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

    private var _addButtonVisible = MutableLiveData<Boolean>(false)
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

    fun onClickButton() {
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
        Memo(memoText,time,secret,password.toString()).run {
            Log.d("ButtonTest",this.toString())
        }

    }
}