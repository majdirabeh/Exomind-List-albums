package fr.dev.majdi.testexomind.room

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.dev.majdi.testexomind.model.Album
import fr.dev.majdi.testexomind.model.Picture
import fr.dev.majdi.testexomind.model.User

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Database(entities = [User::class, Album::class, Picture::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun albumDAO(): AlbumDAO
    abstract fun pictureDAO(): PictureDAO
}