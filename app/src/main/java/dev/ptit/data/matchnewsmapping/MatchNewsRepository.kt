package dev.ptit.data.matchnewsmapping

class MatchNewsRepository(
    private val matchNewsDao: MatchNewsDao,
) {

    fun getAllMatchNews() = matchNewsDao.getAllMatchNews()

    suspend fun insertMatchNews(matchNewsEntity: MatchNewsEntity) =
        matchNewsDao.insertMatchNews(matchNewsEntity)

    suspend fun insertMatchNews(matchNewsEntities: List<MatchNewsEntity>) =
        matchNewsDao.insertMatchNews(matchNewsEntities)
}