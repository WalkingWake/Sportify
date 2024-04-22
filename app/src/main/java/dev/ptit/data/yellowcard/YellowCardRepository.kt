package dev.ptit.data.yellowcard

class YellowCardRepository(
    private val yellowCardDao: YellowCardDao,
) {

    fun getAllYellowCards() = yellowCardDao.getAllYellowCards()

    suspend fun insertYellowCard(yellowCardEntity: YellowCardEntity) =
        yellowCardDao.insertYellowCard(yellowCardEntity)

    suspend fun insertYellowCards(yellowCardEntities: List<YellowCardEntity>) =
        yellowCardDao.insertYellowCards(yellowCardEntities)
}