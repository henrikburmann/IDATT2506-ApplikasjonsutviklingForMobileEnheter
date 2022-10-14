package ntnu.henriabu.arbeidskrav3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.view.get

class MainActivity : Activity() {
    private var friends: MutableList<Person> = mutableListOf()
    lateinit var adapter: ArrayAdapter<Person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initAddFriendButton()

    }



    fun initAddFriendButton(){
        val button = findViewById<Button>(R.id.newFriendButton)
        button.setOnClickListener{
            /*val newFriend = Person("Navn", "1234")
            friends.add(newFriend)
            adapter.notifyDataSetChanged()*/
            val intent = Intent(".AddFriendActivity")
            startActivityForResult(intent, 1)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1){
            if (resultCode != RESULT_OK) {
                Log.e("onActivityResult()", "Æsj da")
                return
            } else {
                val name = data!!.getStringExtra("name");
                val dateOfBirth = data!!.getStringExtra("dateOfBirth");
                if (name != null && dateOfBirth != null) {
                    val newFriend = Person(name, dateOfBirth)
                    friends.add(newFriend)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        if (requestCode == 2){
            if (resultCode != RESULT_OK) {
                Log.e("onActivityResult()", "Æsj da")
                return
            }
            else {
                val name = data!!.getStringExtra("name");
                val dateOfBirth = data!!.getStringExtra("dateOfBirth");
                if (name != null && dateOfBirth != null) {
                    val p = data!!.getIntExtra("index", 10)
                    friends[p].name = name
                    friends[p].dateOfBirth = dateOfBirth
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initList(){
        val person = Person("Henrik", "22-01-22")
        val person2 = Person("Menneske", "01-01-1970")
        friends.add(person)
        friends.add(person2)
        val friendsListView = findViewById<ListView>(R.id.friendsListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        friendsListView.adapter = adapter
        friendsListView.choiceMode = ListView.CHOICE_MODE_SINGLE
        friendsListView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            val intent = Intent(".EditFriendActivity")
            intent.putExtra("index", position)
            startActivityForResult(intent, 2)
        }
    }


}

class Person(name: String, dateOfBirth: String){
    var name = name;
    var dateOfBirth = dateOfBirth

    override fun toString(): String {
        return name + "\n" + dateOfBirth
    }

}