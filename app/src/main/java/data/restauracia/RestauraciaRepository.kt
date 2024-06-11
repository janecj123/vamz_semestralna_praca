package data.restauracia


import kotlinx.coroutines.flow.Flow

class RestauraciaRepository(private val restauraciaDao: RestauraciaDao) :
    RestauraciaRepositoryInterface {
    override fun getZoznamRestauracii(): Flow<List<Restauracia>> =
        restauraciaDao.getZoznamRestauracii()

    override fun getRestauracia(restauraciaID: Int): Flow<Restauracia?> =
        restauraciaDao.getRestauracia(restauraciaID)

    override suspend fun insertRestauracia(restauracia: Restauracia) =
        restauraciaDao.insert(restauracia)

    override suspend fun deleteRestauracia(restauracia: Restauracia) =
        restauraciaDao.delete(restauracia)


    override suspend fun updateRestauracia(restauracia: Restauracia) =
        restauraciaDao.update(restauracia)

}