package ran.am.ariadb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Temple::class], version = 1)
abstract class TempleDatabase : RoomDatabase()  {
    abstract fun templedao(): TempleDAO
}

