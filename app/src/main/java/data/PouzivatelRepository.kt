package data

import kotlinx.coroutines.flow.Flow

interface PouzivatelRepository {

        fun getAllItemsStream(): Flow<List<Pouzivatel>>

        fun getItemStream(meno: String): Flow<Pouzivatel?>

        suspend fun insertItem(pouzivatel: Pouzivatel)

        suspend fun deleteItem(pouzivatel: Pouzivatel)

        suspend fun updateItem(pouzivatel: Pouzivatel)

}