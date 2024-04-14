package dev.ptit.data.league


class LeagueRepository(
    private val leagueDao: LeagueDao
) {
    fun getAllLeagues() = leagueDao.getAllLeagues()

    suspend fun insertLeague(league: LeagueEntity) = leagueDao.insertLeague(league)

    suspend fun insertLeagues(leagues: List<LeagueEntity>) = leagueDao.insertLeagues(leagues)
}