package ntnu.henriabu.arbeidskrav4

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {
    private var beerNames: Array<String> = arrayOf()
    private var beerInfo: Array<String> = arrayOf()
    //private lateinit var images: TypedArray
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        beerNames = resources.getStringArray(R.array.names)
        beerInfo = resources.getStringArray(R.array.descriptions)
        //images = resources.obtainTypedArray(R.array.pictures)
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        putInformation(0)
        super.onViewCreated(view, savedInstanceState)
    }

    fun putInformation(index: Int){
        val title = beerNames[index]
        val info = beerInfo[index]
        val images = resources.obtainTypedArray(R.array.pictures)
        val image = images.getDrawable(index)
        requireView().findViewById<TextView>(R.id.beerName).text = title
        requireView().findViewById<ImageView>(R.id.beerImage).setImageDrawable(image)
        requireView().findViewById<TextView>(R.id.beerInfo).text = info
        images.recycle()
    }
}