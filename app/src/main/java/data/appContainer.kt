package data

import android.content.Context

interface AppContainer {
    val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface
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
}
