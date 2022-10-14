package ntnu.henriabu.a2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

class RandomNumberActivity : Activity(){
    var upperLimit = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)
        upperLimit = intent.getIntExtra("Upper Limit", 20)
        randomNumber(upperLimit)
    }
    fun randomNumber(upperLimit: Int){

        val value = (0..upperLimit).random()
        val intent = Intent()
        intent.putExtra("randomNumber", value)
        setResult(RESULT_OK, intent)
        finish()
    }


}