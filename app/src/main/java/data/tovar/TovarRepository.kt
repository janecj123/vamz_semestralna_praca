package data.tovar


import kotlinx.coroutines.flow.Flow

class TovarRepository(private val tovarDao: TovarDao) :
    TovarRepositoryInterface {
    override fun getZoznamTovaru(): Flow<List<Tovar>> = tovarDao.getZoznamTovarov()

    override fun getTovar(tovarID: Int): Flow<Tovar?> = tovarDao.getTovar(tovarID)

    override suspend fun insertTovar(tovar: Tovar) = tovarDao.insert(tovar)

    override suspend fun deleteTovar(tovar: Tovar) = tovarDao.delete(tovar)

    override suspend fun updateTovar(tovar: Tovar) = tovarDao.update(tovar)

}