package com.example.data

import android.database.sqlite.SQLiteException
import com.example.domain.User
import java.util.concurrent.Executor

class UserRepository(
    private val userDao: UserDao,
    private val executor: Executor
) {

    fun validateCredentials(email: String, password: String, onSuccess: (Boolean) -> Unit) {
        executor.execute {
            val user = userDao.validateUser(email)

            if(user != null) {
                onSuccess(user.password == password)
            } else {
                onSuccess(false)
            }
        }
    }

    fun addUser(user: User, onSuccess: () -> Unit, onError: () -> Unit = {}) {
        executor.execute {
            try {
                userDao.createUser(user)
                onSuccess()
            } catch (e: SQLiteException) {
                e.printStackTrace()
                onError()
            }
        }
    }
}
