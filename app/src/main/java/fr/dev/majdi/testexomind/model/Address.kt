package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "Address")
data class Address(
    @ColumnInfo(name = "city") var city: String,
    @Embedded var geo: Geo,
    @ColumnInfo(name = "street") var street: String,
    @ColumnInfo(name = "suite") var suite: String,
    @ColumnInfo(name = "zipcode") var zipcode: String
)