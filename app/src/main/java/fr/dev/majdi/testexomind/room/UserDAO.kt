package fr.dev.majdi.testexomind.room

import androidx.room.*
import fr.dev.majdi.testexomind.model.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("DELETE from user WHERE id=:id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM user WHERE id=:id")
    fun getById(id: Int): Single<User>

    @Update()
    fun update(user: User)

    @Query("DELETE from user")
    fun deleteUsers()

}