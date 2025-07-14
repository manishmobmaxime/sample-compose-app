package com.compose.app.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.app.data.model.common.CountryModel

@Entity(tableName = "countries") // Define the table name for this entity
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = "country_code") // Maps this property to a column named 'country_code'
    val countryCode: Int? = null,

    @ColumnInfo(name = "country_name")
    val countryName: String? = null,

    @ColumnInfo(name = "country_dialing_code")
    val countryDialingCode: String? = null,

    @ColumnInfo(name = "two_leter_country_code")
    val twoLeterCountryCode: String? = null,

    @ColumnInfo(name = "three_leter_country_code")
    val threeLeterCountryCode: String? = null,

    @ColumnInfo(name = "is_buyer")
    val isBuyer: Boolean? = null,

    @ColumnInfo(name = "is_selected")
    val isSelected: Boolean? = null
)