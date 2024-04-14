package dev.ptit.data.leagueteammapping

class LeagueTeamRepository(
    private val leagueTeamDao: LeagueTeamDao
) {
    fun getAllLeagueTeams() = leagueTeamDao.getAllLeagueTeams()
    suspend fun insertLeagueTeam(leagueTeam: LeagueTeamEntity) =
        leagueTeamDao.insertLeagueTeam(leagueTeam)

    suspend fun insertLeagueTeams(leagueTeams: List<LeagueTeamEntity>) =
        leagueTeamDao.insertLeagueTeams(leagueTeams)
}