package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Picture")
data class Picture(
    @ColumnInfo(name = "albumId")  val albumId: Int,
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "thumbnailUrl")  val thumbnailUrl: String,
    @ColumnInfo(name = "title")  val title: String,
    @ColumnInfo(name = "url")  val url: String
)