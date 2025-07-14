package com.compose.app.data.mappers

import com.compose.app.data.local.database.entities.CountryEntity
import com.compose.app.data.model.common.CountryModel

/**
 * Extension function to convert a CountryModel to a CountryEntity.
 * This is useful when inserting data from your network/serialization layer into the database.
 */
fun CountryModel.toEntity(): CountryEntity {
    return CountryEntity(
        countryCode = this.countryCode,
        countryName = this.countryName,
        countryDialingCode = this.countryDialingCode,
        twoLeterCountryCode = this.twoLeterCountryCode,
        threeLeterCountryCode = this.threeLeterCountryCode,
        isBuyer = this.isBuyer
    )
}

/**
 * Extension function to convert a CountryEntity to a CountryModel.
 * This is useful when retrieving data from the database and converting it back
 * to your network/serialization model for use in other parts of the app.
 */
fun CountryEntity.toModel(): CountryModel {
    return CountryModel(
        countryCode = this.countryCode,
        countryName = this.countryName,
        countryDialingCode = this.countryDialingCode,
        twoLeterCountryCode = this.twoLeterCountryCode,
        threeLeterCountryCode = this.threeLeterCountryCode,
        isBuyer = this.isBuyer
    )
}