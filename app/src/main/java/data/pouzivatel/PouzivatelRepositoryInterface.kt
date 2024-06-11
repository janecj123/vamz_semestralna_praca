package data.pouzivatel


import kotlinx.coroutines.flow.Flow


interface PouzivatelRepositoryInterface {

        fun getPouzivatelia(): Flow<List<Pouzivatel>>

        fun getPouzivatel(meno: String): Flow<Pouzivatel?>


        suspend fun insertPouzivatel(pouzivatel: Pouzivatel)

        suspend fun deletePouzivatel(pouzivatel: Pouzivatel)

        suspend fun updatePouzivatel(pouzivatel: Pouzivatel)

}