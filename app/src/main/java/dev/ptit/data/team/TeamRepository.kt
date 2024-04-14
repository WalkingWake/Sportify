package dev.ptit.data.team

class TeamRepository(
    private val teamDao: TeamDao
) {
    fun getAllTeams() = teamDao.getAllTeams()
    suspend fun insertTeam(team: TeamEntity) = teamDao.insertTeam(team)
    suspend fun insertTeams(teams: List<TeamEntity>) = teamDao.insertTeams(teams)
}