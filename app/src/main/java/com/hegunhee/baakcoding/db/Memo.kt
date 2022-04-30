package com.hegunhee.baakcoding.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Memo(
    val data : String,
    val time : String,
    val secret : Boolean,
    val secretPassWord : String,
    @PrimaryKey(autoGenerate = true)
    val primary_key : Int = 0
) : Parcelable{
}