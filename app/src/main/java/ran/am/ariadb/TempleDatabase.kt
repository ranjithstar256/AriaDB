package ran.am.ariadb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Temple::class], version = 1)
abstract class TempleDatabase : RoomDatabase()  {
    abstract fun templedao(): TempleDAO

    companion object {

        private var INSTANCE: TempleDatabase? = null

        fun getInstance(context: Context): TempleDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TempleDatabase::class.java,
                        "templedataa.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

