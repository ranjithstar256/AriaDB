package ran.am.ariadb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temple_table")
data class Temple (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "TempleName")
    var templeName: String,

    @ColumnInfo(name = "Location")
    var location: String,

    @ColumnInfo(name = "MainGod")
    var mainGod: String

)