package dev.ptit.data.goal

class GoalRepository(
    private val goalDao: GoalDao,
) {

    fun getAllGoals() = goalDao.getAllGoals()

    suspend fun insertGoal(goalEntity: GoalEntity) =
        goalDao.insertGoal(goalEntity)

    suspend fun insertGoals(goalEntities: List<GoalEntity>) =
        goalDao.insertGoals(goalEntities)
}