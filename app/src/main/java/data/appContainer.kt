package data

import android.content.Context
import data.pouzivatel.PouzivatelDatabase
import data.pouzivatel.PouzivatelRepository
import data.pouzivatel.PouzivatelRepositoryInterface
import data.restauracia.RestauraciaDatabase
import data.restauracia.RestauraciaRepository
import data.restauracia.RestauraciaRepositoryInterface
import data.tovar.TovarDatabase
import data.tovar.TovarRepository
import data.tovar.TovarRepositoryInterface

interface AppContainer {
    val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface
    val restauraciaRepositoryInterface: RestauraciaRepositoryInterface
    val tovarRepositoryInterface: TovarRepositoryInterface
}


class AppDataContainer(private val context: Context) : AppContainer {

    override val pouzivatelRepositoryInterface: PouzivatelRepositoryInterface by lazy {
        PouzivatelRepository(PouzivatelDatabase.getDatabase(context).pouzivatelDao())
    }

    override val restauraciaRepositoryInterface: RestauraciaRepositoryInterface by lazy {
        RestauraciaRepository(RestauraciaDatabase.getDatabase(context).restauraciaDao())
    }

    override val tovarRepositoryInterface: TovarRepositoryInterface by lazy {
        TovarRepository(TovarDatabase.getDatabase(context).tovarDao())
    }
}
