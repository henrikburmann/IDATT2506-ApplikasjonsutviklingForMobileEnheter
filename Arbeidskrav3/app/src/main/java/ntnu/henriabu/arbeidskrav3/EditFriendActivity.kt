package ntnu.henriabu.arbeidskrav3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EditFriendActivity: Activity() {
    lateinit var nameView: EditText
    lateinit var dateOfBirthView: EditText
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = intent.getIntExtra("index", 1)
        setContentView(R.layout.activity_edit_friend)
        nameView = findViewById<View>(R.id.EditNameEditText) as EditText
        dateOfBirthView = findViewById<View>(R.id.EditDateOfBirthEditText) as EditText
        initConfirmButton()
        initCancelButton()
    }

    fun initConfirmButton(){
        val button = findViewById<Button>(R.id.EditConfirmFriendButton)
        button.setOnClickListener {
            val name = nameView.text.toString()
            val dateOfBirth = dateOfBirthView.text.toString()
            val intent = Intent()
            intent.putExtra("index", index)
            intent.putExtra("name", name)
            intent.putExtra("dateOfBirth", dateOfBirth)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
    fun initCancelButton(){
        val button = findViewById<Button>(R.id.EditGetBackButton)
        button.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_CANCELED, intent)
            finish() }
    }

}