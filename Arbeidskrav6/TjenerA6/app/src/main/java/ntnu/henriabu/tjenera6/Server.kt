package ntnu.henriabu.tjenera6


import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val textView: TextView, private val PORT: Int = 12345) {

    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulatoren med
     *
     * ```
     * ui = "noe"
     * ```
     */
    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }

    fun start() {
        val connections = arrayListOf<Socket>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ui = "Starter Tjener ..."
                // "innapropriate blocking method call" advarsel betyr at tråden
                // stopper helt opp og ikke går til neste linje før denne fullfører, i dette
                // eksempelet er ikke dette så farlig så vi ignorerer advarselen.
                ServerSocket(PORT).use { serverSocket: ServerSocket ->
                    while (true){
                        val clientSocket = serverSocket.accept()
                        connections.add(clientSocket)
                        ui = "$clientSocket koblet seg på"
                        ClientHandler(clientSocket, connections).start()
                        delay(10000)
                        ui = "Venter på neste..."
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

/*	private fun readFromClient(socket: Socket) {
		val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
		val message = reader.readLine()
		ui = "Klienten sier:\n$message"
	}*/

    private fun sendToClient(socket: Socket, message: String) {
        val writer = PrintWriter(socket.getOutputStream(), true)
        writer.println(message)
        ui = "Sendte følgende til klienten:\n$message"
    }
}