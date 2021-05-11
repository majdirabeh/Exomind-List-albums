package fr.dev.majdi.testexomind.room

import androidx.room.*
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Dao
interface PictureDAO {
    @Query("SELECT * FROM picture WHERE  albumId=:albumId")
    fun getPictureByAlbumId(albumId: Int): Flowable<List<Picture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(picture: Picture)

    @Query("DELETE from picture WHERE id=:id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM picture WHERE id=:id")
    fun getById(id: Int): Single<Picture>

    @Update()
    fun update(picture: Picture)

    @Query("DELETE from picture")
    fun deletePictures()

}