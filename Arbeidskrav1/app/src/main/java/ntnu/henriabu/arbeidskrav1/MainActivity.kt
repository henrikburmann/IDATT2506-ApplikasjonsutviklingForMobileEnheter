package ntnu.henriabu.arbeidskrav1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import ntnu.henriabu.arbeidskrav1.R

class MainActivity : AppCompatActivity() {
    val firstName = "Henrik"
    val lastName = "Burmann"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(meny: Menu): Boolean {
        super.onCreateOptionsMenu(meny)
        meny.add(firstName)
        meny.add(lastName)
        Log.d("Arbeidskrav 1" , "meny laget")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals(firstName)) {
            Log.w("Arbiedskrav 1", firstName)
        }
        else if (item.title.equals(lastName)) {
            Log.e("Arbiedskrav 1", lastName)
        }
        return true
    }
}