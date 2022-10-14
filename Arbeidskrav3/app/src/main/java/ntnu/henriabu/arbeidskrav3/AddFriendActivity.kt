package ntnu.henriabu.arbeidskrav3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddFriendActivity: Activity() {

    lateinit var nameView: EditText
    lateinit var dateOfBirthView: EditText
    lateinit var confirmButton: Button
    lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        nameView = findViewById<View>(R.id.NameEditText) as EditText
        dateOfBirthView = findViewById<View>(R.id.DateOfBirthEditText) as EditText
        initConfirmButton()
        initCancelButton()
    }

    fun initConfirmButton(){
        val button = findViewById<Button>(R.id.ConfirmFriendButton)
        button.setOnClickListener {
            val name = nameView.text.toString()
            val dateOfBirth = dateOfBirthView.text.toString()
            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("dateOfBirth", dateOfBirth)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun initCancelButton(){
        val button = findViewById<Button>(R.id.GetBackButton)
        button.setOnClickListener {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish() }
    }


}