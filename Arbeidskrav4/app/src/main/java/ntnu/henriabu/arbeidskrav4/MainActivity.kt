package ntnu.henriabu.arbeidskrav4

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity(), NameListFragment.OnItemClickedListener {
    private var clickedBeer = 0;
    private var numberOfBeers = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOrientation(resources.configuration)
        numberOfBeers = resources.getStringArray(R.array.names).size
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_previous_item -> previousItem()
            R.id.menu_next_item -> nextItem()
            else -> return false
        }
        return true
    }

    private fun previousItem(){
        var previous = clickedBeer - 1
        if(previous == -1) previous = numberOfBeers - 1
        onBeerItemClick(previous)
    }

    private fun nextItem(){
        var next = clickedBeer + 1
        if (next == numberOfBeers) next = 0
        onBeerItemClick(next)
    }


    override fun onBeerItemClick(position: Int) {
        clickedBeer = position
        val infoFragment = supportFragmentManager.findFragmentById(R.id.info) as InfoFragment
        infoFragment.putInformation(position)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }

    private fun setOrientation(config: Configuration) {
        val isPortrait: Boolean = config.orientation == Configuration.ORIENTATION_PORTRAIT
        if (isPortrait) setContentView(R.layout.activity_main) else setContentView(R.layout.horizontal_activity_main)
    }
}