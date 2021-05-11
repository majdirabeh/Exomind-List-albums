package fr.dev.majdi.testexomind.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey var id: Int,
    @Embedded var address: Address,
    @Embedded var company: Company,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "website") var website: String
)