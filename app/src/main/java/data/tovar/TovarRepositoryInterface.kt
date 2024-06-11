package data.tovar


import kotlinx.coroutines.flow.Flow


interface TovarRepositoryInterface {

    fun getZoznamTovaru(): Flow<List<Tovar>>

    fun getTovar(tovarID: Int): Flow<Tovar?>


    suspend fun insertTovar(tovar: Tovar)

    suspend fun deleteTovar(tovar: Tovar)

    suspend fun updateTovar(tovar: Tovar)

}