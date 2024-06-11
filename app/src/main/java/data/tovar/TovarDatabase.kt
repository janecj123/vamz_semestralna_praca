package data.tovar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.restauracia.Restauracia
import data.restauracia.RestauraciaDao
import data.restauracia.RestauraciaDatabase

@Database(entities = [Tovar::class], version = 1, exportSchema = false)
abstract class TovarDatabase : RoomDatabase() {

    abstract fun tovarDao(): TovarDao

    companion object {
        @Volatile
        private var Instance: TovarDatabase? = null

        fun getDatabase(context: Context): TovarDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TovarDatabase::class.java, "tovar_database")
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