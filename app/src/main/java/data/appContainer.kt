package data

import android.content.Context
import data.pouzivatel.PouzivatelDatabase
import data.pouzivatel.PouzivatelRepository
import data.pouzivatel.PouzivatelRepositoryInterface
import data.restauracia.RestauraciaDatabase
import data.restauracia.RestauraciaRepository
import data.restauracia.RestauraciaRepositoryInterface

interface AppContainer {
    val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface
    val restauraciaRepositoryInterface: RestauraciaRepositoryInterface
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface by lazy {
        PouzivatelRepository(PouzivatelDatabase.getDatabase(context).pouzivatelDao())
    }

    override val restauraciaRepositoryInterface: RestauraciaRepositoryInterface by lazy {
        RestauraciaRepository(RestauraciaDatabase.getDatabase(context).restauraciaDao())
    }
}
