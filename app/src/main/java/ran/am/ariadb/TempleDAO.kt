package ran.am.ariadb

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TempleDAO {

    @Insert
    suspend fun insertNewTemple(temple: Temple)



}
