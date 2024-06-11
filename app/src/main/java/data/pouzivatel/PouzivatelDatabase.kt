package data.pouzivatel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pouzivatel::class], version = 1, exportSchema = false)
abstract class PouzivatelDatabase : RoomDatabase() {

    abstract fun pouzivatelDao(): PouzivatelDao

    companion object {
        @Volatile
        private var Instance: PouzivatelDatabase? = null

        fun getDatabase(context: Context): PouzivatelDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PouzivatelDatabase::class.java, "pouzivatel_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
