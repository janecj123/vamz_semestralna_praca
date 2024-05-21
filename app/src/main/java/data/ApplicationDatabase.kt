package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


    @Database(entities = [Pouzivatel::class], version = 1, exportSchema = false)
    abstract class ApplicationDatabase : RoomDatabase() {

        abstract fun pouzivatelDao(): PouzivatelDao

        companion object {
            @Volatile
            private var Instance: ApplicationDatabase? = null

            fun getDatabase(context: Context): ApplicationDatabase {
                // if the Instance is not null, return it, otherwise create a new database instance.
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, ApplicationDatabase::class.java, "pouzivatel_database")
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
