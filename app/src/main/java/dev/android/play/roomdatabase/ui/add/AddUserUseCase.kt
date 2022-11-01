package dev.android.play.roomdatabase.ui.add

import dev.android.play.roomdatabase.data.AddressEntity
import dev.android.play.roomdatabase.data.UserEntity
import dev.android.play.roomdatabase.db.AddressDao
import dev.android.play.roomdatabase.db.UserDao
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userDao: UserDao,
    private val addressDao: AddressDao
) {
    suspend fun insertUser(userEntity: UserEntity) = userDao.insertUser(userEntity)
    suspend fun insertAddress(address: AddressEntity) = addressDao.insertAddress(address)
}