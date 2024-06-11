package data.tovar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import data.restauracia.Restauracia
import kotlinx.coroutines.flow.Flow

@Dao
interface TovarDao {
    @Query("SELECT * from Tovar order by nazov")
    fun getZoznamTovarov(): Flow<List<Tovar>>


    @Query("SELECT * from Tovar WHERE tovarID = :tovarID")
    fun getTovar(tovarID: Int): Flow<Tovar>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tovar: Tovar)

    @Update
    suspend fun update(tovar: Tovar)

    @Delete
    suspend fun delete(tovar: Tovar)
}