package ran.am.ariadb

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.Room
import kotlinx.coroutines.launch
import ran.am.ariadb.ui.theme.AriaDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AriaDBTheme {

                val db= Room.databaseBuilder(applicationContext,
                TempleDatabase::class.java,
                "templedata.db"
                    ).build()

                saveTempleData(db, applicationContext)

            }
        }
    }
}
@Composable
fun saveTempleData(db: TempleDatabase, applicationContext: Context){

    val scope = rememberCoroutineScope()

    var templename by remember { mutableStateOf("") }
    var templelocation by remember { mutableStateOf("") }
    var maingod by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {

        TextField(value = templename, onValueChange = { templename = it })
        TextField(value = templelocation, onValueChange = { templelocation = it })
        TextField(value = maingod, onValueChange = { maingod = it })


        Button(onClick = {
            val Temple = Temple(0, templename, templelocation, maingod)

            scope.launch {
                db.templedao().insertNewTemple( Temple(0, templename, templelocation, maingod))
            }

            Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()


        }) {
            Text(text = "Save")
        }
    }
}


// data -> data -> ran.am.ariadb -> database -> templedata.db