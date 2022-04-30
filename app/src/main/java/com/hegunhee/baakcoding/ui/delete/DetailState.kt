package com.hegunhee.baakcoding.ui.delete

sealed class DetailState {
    object Uninitalized : DetailState()
    object Detail : DetailState()
}
