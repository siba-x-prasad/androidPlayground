package dev.android.play.roomdatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.android.play.roomdatabase.data.AddressEntity
import dev.android.play.roomdatabase.data.UserEntity

@Dao
interface AddressDao {

    @Query("SELECT * FROM address")
    suspend fun getAllAddress(): List<AddressEntity>

    @Query("SELECT * FROM address WHERE id IN (:userIds)")
    suspend fun getAllAddressByIds(userIds: IntArray): List<AddressEntity>

    @Query(
        "SELECT * FROM address WHERE fName LIKE :first LIMIT 1"
    )
    suspend fun findAddressByName(first: String): AddressEntity

    @Insert
    suspend fun insertAllAddress(vararg addresses: AddressEntity)

    @Insert
    suspend fun insertAddress(vararg addresses: AddressEntity)

    @Delete
    suspend fun deleteAddress(user: AddressEntity)

}