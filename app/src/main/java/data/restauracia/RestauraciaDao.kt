package data.restauracia

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import data.pouzivatel.Pouzivatel
import kotlinx.coroutines.flow.Flow

@Dao
interface RestauraciaDao {
    @Query("SELECT * from Restauracia order by nazov ASC")
    fun getZoznamRestauracii(): Flow<List<Restauracia>>


    @Query("SELECT * from Restauracia WHERE restauraciaID = :restauraciaID")
    fun getRestauracia(restauraciaID: Int): Flow<Restauracia>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restauracia: Restauracia)

    @Update
    suspend fun update(restauracia: Restauracia)

    @Delete
    suspend fun delete(restauracia: Restauracia)
}