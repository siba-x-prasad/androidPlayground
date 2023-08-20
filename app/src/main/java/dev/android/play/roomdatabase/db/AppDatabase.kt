package dev.android.play.roomdatabase.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.android.play.roomdatabase.data.AddressEntity
import dev.android.play.roomdatabase.data.UserEntity


@Database(entities = [UserEntity::class, AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun addressDao(): AddressDao
}