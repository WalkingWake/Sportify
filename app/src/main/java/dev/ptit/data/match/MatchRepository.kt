package dev.ptit.data.match

class MatchRepository(
    private val matchDao: MatchDao
) {

    fun getAllMatches() = matchDao.getAllMatches()

    suspend fun insertMatch(matchEntity: MatchEntity) = matchDao.insertMatch(matchEntity)

    suspend fun insertMatches(matchEntities: List<MatchEntity>) =
        matchDao.insertMatches(matchEntities)
}