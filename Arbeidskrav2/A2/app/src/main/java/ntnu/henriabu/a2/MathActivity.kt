package ntnu.henriabu.a2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MathActivity: Activity() {
    var num1: Int = 3
    var num2: Int= 5
    var userAnswer = 8;
    var upperLimit = 50;
    lateinit var num1v: TextView
    lateinit var num2v: TextView
    lateinit var userAnswerv: EditText
    lateinit var upperLimitv: EditText
    var isFirstNumber: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)
        num1v =  findViewById<View>(R.id.Num1) as TextView
        num2v = findViewById<View>(R.id.tall2) as TextView
        upperLimitv = findViewById<View>(R.id.upper_limit_input) as EditText
        userAnswerv = findViewById(R.id.user_answer)
        userAnswerv.setText(userAnswer.toString())
        num1v.text = num1.toString();
        num2v.text = num2.toString();
        upperLimitv.setText(upperLimit.toString());

    }


    fun newRandomNum(){
        isFirstNumber = true
        val intent = Intent(".RandomNumberActivity")
        upperLimit = upperLimitv.text.toString().toInt();
        intent.putExtra("Upper Limit", upperLimit)
        startActivityForResult(intent, 20)
        startActivityForResult(intent, 20)
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != RESULT_OK){
            Log.e("onActivityResult()", "Æsj da")
            return
        }
        else{
            val number = data!!.getIntExtra("randomNumber", 1);
            if (isFirstNumber) {
                num1 = number
                num1v.text = num1.toString()
                isFirstNumber = false
            }
            else{
                num2 = number
                num2v.text = num2.toString()
            }
        }
    }
    fun onClickAddition(view: View?){
        userAnswer = userAnswerv.text.toString().toInt();
        if (userAnswer == (num1 + num2)){
            Toast.makeText(this,"Riktig!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Feil. Riktig svar er: " + (num1 + num2), Toast.LENGTH_LONG).show()

        }
        newRandomNum()
    }
    fun onClickMultiply(view: View?){
        userAnswer = userAnswerv.text.toString().toInt();

        if (userAnswer == (num1 * num2)){
            Toast.makeText(this,"Riktig!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Feil. Riktig svar er: " + (num1 + num2), Toast.LENGTH_LONG).show()
        }
        newRandomNum()
    }
}