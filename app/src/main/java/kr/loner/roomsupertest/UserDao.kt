package kr.loner.roomsupertest

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN(:userIds) LIMIT 5")
    fun loadAllByIds(userIds: IntArray): List<User>


    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListAll(list: List<User>)

    @Delete
    fun delete(user: User)

    @Update
    fun updateUsers(vararg users: User)

    @Query("SELECT * FROM user WHERE age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :search " +
            "OR last_name LIKE :search")
    fun findUserWithName(search: String): List<User>

}
