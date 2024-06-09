package data.restauracia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.pouzivatel.PouzivatelDao
import data.pouzivatel.PouzivatelDatabase

@Database(entities = [Restauracia::class], version = 1, exportSchema = false)
abstract class RestauraciaDatabase : RoomDatabase() {

    abstract fun restauraciaDao(): RestauraciaDao

    companion object {
        @Volatile
        private var Instance: RestauraciaDatabase? = null

        fun getDatabase(context: Context): RestauraciaDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RestauraciaDatabase::class.java, "restauracia_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}