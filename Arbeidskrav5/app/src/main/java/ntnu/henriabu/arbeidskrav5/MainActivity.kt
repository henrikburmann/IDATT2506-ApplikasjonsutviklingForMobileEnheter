package ntnu.henriabu.arbeidskrav5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.CacheResponse
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onButtonClicked()
    }

    fun onButtonClicked(){
        val button = findViewById<Button>(R.id.personbutton)
        button.setOnClickListener {
            val name: String = (findViewById<EditText>(R.id.name) as EditText).text.toString()
            val accountNumber: String =
                (findViewById<EditText>(R.id.account_number) as EditText).text.toString()
            val intent = Intent(".GameActivity")
            if (name.isNotEmpty() && accountNumber.isNotEmpty()){
            intent.putExtra("name", name)
            intent.putExtra("card", accountNumber)
            startActivity(intent)}
            else{
                var toast = Toast.makeText(applicationContext,"Navn og kort kan ikke være tomt", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }



}