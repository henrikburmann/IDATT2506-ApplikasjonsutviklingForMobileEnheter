package ntnu.henriabu.arbeidskrav4

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class NameListFragment : ListFragment   () {
    private var beerNames : Array<String> = arrayOf();
    private var listener: OnItemClickedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beerNames = resources.getStringArray(R.array.names)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, beerNames)
        }
    }


    interface OnItemClickedListener {
        fun onBeerItemClick(position: Int)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener!!.onBeerItemClick(position)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            activity as OnItemClickedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
            )
        }
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}