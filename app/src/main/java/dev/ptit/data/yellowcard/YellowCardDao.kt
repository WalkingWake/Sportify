package dev.ptit.data.yellowcard

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface YellowCardDao {

    @Query("SELECT * FROM yellow_card")
    fun getAllYellowCards(): Flow<List<YellowCardEntity>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertYellowCard(yellowCardEntity: YellowCardEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertYellowCards(yellowCardEntities: List<YellowCardEntity>)
}