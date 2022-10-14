package ntnu.henriabu.a2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : Activity() {
    var num = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickRandomNumber(v: View){
        val intent = Intent(".RandomNumberActivity")
        val upperLimit = 100
        intent.putExtra("Upper Limit", upperLimit)
        startActivityForResult(intent, num)
    }

    fun onClickToMath(v:View){
        val intent = Intent(".MathActivity")
        startActivity(intent)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != RESULT_OK){
            Log.e("onActivityResult()", "Æsj da")
            return
        }
        else{
            num = data!!.getIntExtra("randomNumber", 1);
            val textView = findViewById<View>(R.id.textView) as TextView
            //Toast.makeText(this,"Ditt tall ble: " + num.toString() , Toast.LENGTH_LONG).show()
            textView.text = num.toString()
        }
    }


}