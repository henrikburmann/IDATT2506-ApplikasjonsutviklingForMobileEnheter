package ntnu.henriabu.klienta6

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val incomingTextView = findViewById<TextView>(R.id.incomingView)
        val sentTextView = findViewById<TextView>(R.id.sentView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        Client(incomingTextView, sentTextView, editText, button).start()
    }
}
