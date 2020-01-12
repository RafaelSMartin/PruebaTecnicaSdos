package com.rsmartin.pruebatecnicasdos.presentation.internal.room

import androidx.room.*
import com.rsmartin.pruebatecnicasdos.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table")
    fun findAllUsers(): MutableList<UserEntity>

    @Query("SELECT * FROM user_table WHERE email =:email")
    fun findUserByEmail(email: String): UserEntity

    @Query("SELECT * FROM user_table WHERE name =:name")
    fun findUserByName(name: String): UserEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UserEntity)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Delete
    fun deleteUser(user: UserEntity)
}