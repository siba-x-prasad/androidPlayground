package dev.android.play.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "fName") val fName: String,
    @ColumnInfo(name = "lName") val lName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "contact") val contact: String
)