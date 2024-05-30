package ran.am.ariadb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TempleDAO {

    @Insert
    suspend fun insertNewTemple(temple: Temple)

    @Query("select MainGod from temple_table where TempleName like :templename")
    suspend fun getmaingod(templename : String): String

    @Update
    suspend fun updateTemple(temple: Temple)

    @Delete
    suspend fun deleteTemple(temple: Temple)

}
