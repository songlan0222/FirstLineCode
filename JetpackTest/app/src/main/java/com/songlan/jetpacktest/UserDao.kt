package com.songlan.jetpacktest

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from User")
    fun getAllUser(): List<User>

    @Query("delete from User where 1=1")
    fun deleteAllUser()

    @Query("select * from User where id = :uId")
    fun getUserById(uId: Long):User
}