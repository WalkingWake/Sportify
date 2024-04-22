package dev.ptit.data.substitution

class SubstitutionRepository(
    private val substitutionDao: SubstitutionDao,
) {

    fun getAllSubstitutions() = substitutionDao.getAllSubstitutions()

    suspend fun insertSubstitution(substitutionEntity: SubstitutionEntity) =
        substitutionDao.insertSubstitution(substitutionEntity)

    suspend fun insertSubstitutions(substitutionEntities: List<SubstitutionEntity>) =
        substitutionDao.insertSubstitutions(substitutionEntities)
}