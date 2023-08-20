package dev.android.play.roomdatabase.ui.show

import dev.android.play.roomdatabase.db.AddressDao
import dev.android.play.roomdatabase.db.UserDao
import javax.inject.Inject

class ShowUserUseCase @Inject constructor(
    private val userDao: UserDao,
    private val addressDao: AddressDao
) {
    suspend fun getAllUsers() = userDao.getAll()
    suspend fun getAllAddress() = addressDao.getAllAddress()
}