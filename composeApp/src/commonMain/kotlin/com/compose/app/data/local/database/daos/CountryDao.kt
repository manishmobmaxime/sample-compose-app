package com.compose.app.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.compose.app.data.local.database.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Update
    suspend fun updateCountry(country: CountryEntity)

    @Delete
    suspend fun deleteCountry(country: CountryEntity)

    @Query("SELECT * FROM countries WHERE country_code = :countryCode") // Updated query
    fun getCountryByCode(countryCode: Int): Flow<CountryEntity?> // Use countryCode for lookup

    @Query("SELECT * FROM countries WHERE isSelected = 1 LIMIT 1")
    fun getSelectedCountry(): Flow<CountryEntity?>

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryEntity>>

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}