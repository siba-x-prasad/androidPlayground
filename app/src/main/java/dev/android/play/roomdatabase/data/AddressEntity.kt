package dev.android.play.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey val id: Int,
    val fName: String,
    val streetName: String,
    val areaName: String,
    val pinCode: String
)
