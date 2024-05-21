package data

import kotlinx.coroutines.flow.Flow

class PouzivatelRepository(private val pouzivatelDao: PouzivatelDao) : PouzivatelRepositoryInterface {
    override fun getPouzivatelia(): Flow<List<Pouzivatel>> = pouzivatelDao.getPouzivatelia()

    override fun getPouzivatel(meno: String): Flow<Pouzivatel?> = pouzivatelDao.getPouzivatel(meno)

    override suspend fun insertPouzivatel(pouzivatel: Pouzivatel) = pouzivatelDao.insert(pouzivatel)

    override suspend fun deletePouzivatel(pouzivatel: Pouzivatel) = pouzivatelDao.delete(pouzivatel)

    override suspend fun updatePouzivatel(pouzivatel: Pouzivatel) = pouzivatelDao.update(pouzivatel)
}