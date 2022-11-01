package dev.android.play.roomdatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.android.play.roomdatabase.data.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<UserEntity>

    @Query(
        "SELECT * FROM User WHERE fName LIKE :first AND " +
                "lName LIKE :last LIMIT 1"
    )
    suspend fun findByName(first: String, last: String): UserEntity

    @Insert
    suspend fun insertAll(vararg users: UserEntity)

    @Insert
    suspend fun insertUser(vararg users: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}