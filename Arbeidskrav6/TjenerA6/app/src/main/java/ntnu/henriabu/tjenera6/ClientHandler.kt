package ntnu.henriabu.tjenera6


import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.*
import java.net.Socket
import javax.security.auth.callback.Callback

class ClientHandler(private val serverSocket: Socket, private val connections: ArrayList<Socket>) {
    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                serverSocket.use {
                    readFromClient(serverSocket) {
                            message: String ->
                        Log.i("Msg", message)
                        sendToEveryone(message)
                    }
                }
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun sendToEveryone(message: String){
        for(socket in connections){
            if(socket != serverSocket){
                val writer = PrintWriter(socket.getOutputStream(), true)
                writer.println(message)
            }
        }
    }

    companion object {
        fun readFromClient(socket: Socket, callback: (message: String) -> Unit){
            CoroutineScope(IO).let {
                while (true){
                    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                    val message = reader.readLine()
                    if(message != null) callback(message)
                }
            }
        }
    }
}