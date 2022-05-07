package com.hegunhee.baakcoding.ui.delete

import android.util.Log
import androidx.lifecycle.*
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
        memoLength.value = memo.data.length
        if(memo.secret){
            passwordText.value = memo.secretPassWord
        }
        memoText = memo.data
    }


    private var memoLength = MutableLiveData<Int>()
    val memoTextLength : LiveData<String> = Transformations.map(memoLength){
        "글자수 : $it"
    }

    val addButtonVisible: LiveData<Boolean> = Transformations.map(memoLength){
        it != 0
    }

    var passwordText = MutableLiveData<String>("")

    var memoText: String = ""


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            memoLength.value =  0
            memoText = ""
        } else {
            memoText = s.toString()
            memoLength.value = s.length
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