package dev.ptit.data.goal

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @androidx.room.Query(value = "SELECT * FROM goal")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goalEntity: GoalEntity)

    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertGoals(goalEntities: List<GoalEntity>)
}