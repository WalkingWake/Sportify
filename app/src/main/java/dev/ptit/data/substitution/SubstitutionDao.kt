package dev.ptit.data.substitution

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface SubstitutionDao {

    @Query("SELECT * FROM substitution")
    fun getAllSubstitutions(): Flow<List<SubstitutionEntity>>

    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertSubstitution(substitutionEntity: SubstitutionEntity)

    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertSubstitutions(substitutionEntities: List<SubstitutionEntity>)

}