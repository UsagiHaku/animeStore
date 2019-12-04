package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createUser(user: User)

    @Query("SELECT * FROM users_table WHERE email = :email")
    fun validateUser(email: String): User?

}
