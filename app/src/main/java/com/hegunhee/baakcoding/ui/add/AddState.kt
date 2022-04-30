package com.hegunhee.baakcoding.ui.add

sealed class AddState {
    object Uninitalized : AddState()

    object Add : AddState()
}