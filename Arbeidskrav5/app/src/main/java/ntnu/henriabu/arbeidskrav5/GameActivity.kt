package ntnu.henriabu.arbeidskrav5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
class GameActivity : AppCompatActivity() {
    private var responseString: String = ""
    private val network: HttpWrapper = HttpWrapper(URL)
    private lateinit var name: String
    private lateinit var account: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        name = intent.getStringExtra("name").toString()
        account = intent.getStringExtra("card").toString()
        performRequest(HTTP.GET, requestParametersLogIn(name, account))
        sendGuess()
    }

    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> network.post(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }

            // Endre UI på hovedtråden
            MainScope().launch {
                //setResult(formatJsonString(response))
                findViewById<TextView>(R.id.ResultView).text = response
            }
        }
    }

    fun sendGuess(){
        val button = findViewById<Button>(R.id.answerButton)
        button.setOnClickListener {
            val guessBox = findViewById<TextView>(R.id.userAnswerView) as EditText
            val guess = guessBox.text.toString()
            performRequest(HTTP.GET, requestParametersGuess(guess))
        }
    }

    private fun requestParametersLogIn(name: String, account: String): Map<String, String> {
        return mapOf(
            "navn" to name,
            "kortnummer" to account,
        )
    }

    private fun requestParametersGuess(number: String): Map<String, String>{
        return mapOf(
            "tall" to number
        )
    }
    private fun setResult(response: String?){
        if (response != null) {
            responseString = response
        };
    }
    private fun formatJsonString(str: String): String {
        return try {
            JSONArray(str).toString(4)
        } catch (e: Exception) {
            Log.e("formatJsonString()", e.toString())
            e.message!!
        }
    }

    private fun createUser(){
        
    }
}