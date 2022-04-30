package com.hegunhee.baakcoding.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    val data : String,
    val time : String,
    val secret : Boolean,
    val secretPassWord : String,
    @PrimaryKey(autoGenerate = true)
    val primary_key : Int = 0
) {
}