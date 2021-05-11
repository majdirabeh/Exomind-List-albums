package fr.dev.majdi.testexomind.room

import androidx.room.*
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Dao
interface AlbumDAO {
    @Query("SELECT * FROM album WHERE `userId album`=:userId")
    fun getAlbumsByUserId(userId: Int): Flowable<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: Album)

    @Query("DELETE from album WHERE id=:id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM album WHERE id=:id")
    fun getById(id: Int): Single<Album>

    @Update()
    fun update(album: Album)

    @Query("DELETE from album")
    fun deleteAlbums()

}