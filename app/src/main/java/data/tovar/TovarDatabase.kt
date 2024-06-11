package data.tovar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Tovar::class], version = 1, exportSchema = false)
abstract class TovarDatabase : RoomDatabase() {

    abstract fun tovarDao(): TovarDao

    companion object {
        @Volatile
        private var Instance: TovarDatabase? = null

        fun getDatabase(context: Context): TovarDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TovarDatabase::class.java, "tovar_database")

                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}