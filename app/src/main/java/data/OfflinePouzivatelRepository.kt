package data

import kotlinx.coroutines.flow.Flow

class OfflinePouzivatelRepository(private val pouzivatelDao: PouzivatelDao) : PouzivatelRepository {
    override fun getAllItemsStream(): Flow<List<Pouzivatel>> = pouzivatelDao.getPouzivatelia()

    override fun getItemStream(meno: String): Flow<Pouzivatel?> = pouzivatelDao.getPouzivatel(meno)

    override suspend fun insertItem(pouzivatel: Pouzivatel) = pouzivatelDao.insert(pouzivatel)

    override suspend fun deleteItem(pouzivatel: Pouzivatel) = pouzivatelDao.delete(pouzivatel)

    override suspend fun updateItem(pouzivatel: Pouzivatel) = pouzivatelDao.update(pouzivatel)
}