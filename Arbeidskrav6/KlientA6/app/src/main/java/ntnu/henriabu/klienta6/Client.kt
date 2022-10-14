package ntnu.henriabu.klienta6

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
    private val incomingText: TextView,
    private val sentText: TextView,
    private val editText: EditText,
    private val button: Button,
    private val SERVER_IP: String = "10.0.2.2",
    private val SERVER_PORT: Int = 12345

) {

    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulator med
     *
     * ```
     * ui = "noe"
     * ```
     */

    private var receivedMsg: String? = ""
        set(str) {
            MainScope().launch { incomingText.text = str }
            field = str
        }

    private var ui: String? = ""
        set(str) {
            MainScope().launch { sentText.text = str }
            field = str
        }


    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Kobler til tjener..."
            try {
                Socket(SERVER_IP, SERVER_PORT).use { socket: Socket ->
                    ui = "Koblet til tjener:\n$socket"

                    delay(5000)

                    readFromServer(socket)

                    button.setOnClickListener {
                        sendMessage(socket)
                    }
                    readFromServer(socket){
                        message: String ->
                        receivedMsg += message +"\n"

                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

    private fun readFromServer(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        ui = "Melding fra tjeneren:\n$message"
    }

    private fun sendMessage(socket: Socket) {
        CoroutineScope(IO).launch {
            val message = editText.text.toString()
            val writer = PrintWriter(socket.getOutputStream(), true)
            writer.println(message)
            ui += message + "\n"
        }
    }
    companion object {
        fun readFromServer(socket: Socket, callback: (message: String) -> Unit){
            CoroutineScope(IO).let {
                while (true){
                    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                    val message = reader.readLine()
                    if(message != null) callback(message)
                    else break
                }
            }
        }
    }
}