package data.pouzivatel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PouzivatelDao {
    @Query("SELECT * from Pouzivatel order by pouzivatel")
    fun getPouzivatelia(): Flow<List<Pouzivatel>>

   // @Query("SELECT heslo FROM pouzivatelInfo WHERE meno = :meno")
   // suspend fun overHeslo(meno: String): Pouzivatel?
    
    @Query("SELECT * from Pouzivatel WHERE pouzivatel = :pouzivatel")
    fun getPouzivatel(pouzivatel: String): Flow<Pouzivatel>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Pouzivatel)

    @Update
    suspend fun update(item: Pouzivatel)

    @Delete
    suspend fun delete(item: Pouzivatel)




}