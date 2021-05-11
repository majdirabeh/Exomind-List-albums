package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo

data class Geo(
    @ColumnInfo(name = "lat") val lat: String,
    @ColumnInfo(name = "lng") val lng: String
)