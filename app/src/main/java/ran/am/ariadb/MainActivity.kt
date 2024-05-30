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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ran.am.ariadb.ui.theme.AriaDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AriaDBTheme {
             //   val viewModel = ViewModelProvider(this).get(TempleViewModel::class.java)

               /*val dbb= Room.databaseBuilder(applicationContext,
                TempleDatabase::class.java,
                "templedata.db"
                    ).build()*/

                //dbb.templedao().insertNewTemple(Temple(0, "Kedarnath", "Uttarakhand", "Shiva"))

                val db= TempleDatabase.getInstance(applicationContext)


                val templedao = db.templedao()
                val repository= TempleRepository(templedao)
                val vwmdl = TempleViewModel(repository)

                Column {


                saveTempleData(db, applicationContext,vwmdl)
                retriveMainGod(vwmdl)
                }
            }
        }
    }
}
@Composable
fun saveTempleData(db: TempleDatabase, applicationContext: Context, viewModel: TempleViewModel){

    val scope = rememberCoroutineScope()

    var templename by remember { mutableStateOf("") }
    var templelocation by remember { mutableStateOf("") }
    var maingod by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
         ) {

        TextField(value = templename, onValueChange = { templename = it })
        TextField(value = templelocation, onValueChange = { templelocation = it })
        TextField(value = maingod, onValueChange = { maingod = it })


        Button(onClick = {


            scope.launch {

                viewModel.insertTemple(Temple(0, templename, templelocation, maingod))

                //viewModel..insertStudent(Temple(0, templename, templelocation, maingod))

              //  db.templedao().insertNewTemple( Temple(0, templename, templelocation, maingod))
            }

            Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()


        }) {
            Text(text = "Save")
        }
    }
}

@Composable
fun retriveMainGod(viewModel: TempleViewModel) {
    var templename by remember { mutableStateOf("") }
    var maingod by remember { mutableStateOf("enter main god") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        LaunchedEffect(Unit) {
            viewModel.mainGodinvw.collect {
                maingod = it
            }
        }


        TextField(value = templename, onValueChange = { templename = it })
        Button(onClick = {
            // code for retriving maingod using templename
            // select maingod from Temple where templeName = templename

            viewModel.getmaingod(templename)

        }) {
            Text(text = "Get Main God")
        }
        Text(text = maingod)
    }
}
