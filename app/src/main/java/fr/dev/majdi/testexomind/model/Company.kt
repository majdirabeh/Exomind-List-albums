package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo

data class Company(
    @ColumnInfo(name = "bs") val bs: String,
    @ColumnInfo(name = "catchPhrase") val catchPhrase: String,
    @ColumnInfo(name = "name company") val name: String
)