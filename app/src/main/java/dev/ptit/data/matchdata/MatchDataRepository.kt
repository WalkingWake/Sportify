package dev.ptit.data.matchdata

class MatchDataRepository(
    private val matchDataDao: MatchDataDao,
) {
    fun getAllMatchData() = matchDataDao.getAllMatchData()

    suspend fun insertMatchData(matchDataEntity: MatchDataEntity) =
        matchDataDao.insertMatchData(matchDataEntity)

    suspend fun insertMatchData(matchDataEntities: List<MatchDataEntity>) =
        matchDataDao.insertMatchData(matchDataEntities)
}