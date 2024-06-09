package data.restauracia

import data.pouzivatel.Pouzivatel
import kotlinx.coroutines.flow.Flow

interface RestauraciaRepositoryInterface {

    fun getZoznamRestauracii(): Flow<List<Restauracia>>

    fun getRestauracia(restauraciaID: Int): Flow<Restauracia?>


    suspend fun insertRestauracia(restauracia: Restauracia)

    suspend fun deleteRestauracia(restauracia: Restauracia)

    suspend fun updateRestauracia(restauracia: Restauracia)

}