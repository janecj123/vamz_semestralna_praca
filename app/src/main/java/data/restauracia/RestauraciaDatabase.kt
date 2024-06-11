package data.restauracia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Restauracia::class], version = 1, exportSchema = false)
abstract class RestauraciaDatabase : RoomDatabase() {

    abstract fun restauraciaDao(): RestauraciaDao

    companion object {
        @Volatile
        private var Instance: RestauraciaDatabase? = null

        fun getDatabase(context: Context): RestauraciaDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RestauraciaDatabase::class.java,
                    "restauracia_database"
                )

                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}