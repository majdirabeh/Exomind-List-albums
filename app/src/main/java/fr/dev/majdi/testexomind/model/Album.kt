package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
data class Album(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "userId album") val userId: Int
)