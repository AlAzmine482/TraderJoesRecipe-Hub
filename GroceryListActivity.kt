package com.example.traderjoes20

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.traderjoes20.databinding.ActivityListgroceryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GroceryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListgroceryBinding
    private lateinit var itemView: ListView
    private lateinit var addBtn: Button
    private lateinit var itemEdt: EditText
    private lateinit var database: DatabaseReference
    private lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListgroceryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // on below line we are initializing our variables.
        itemView = findViewById(R.id.userlist)
        addBtn = findViewById(R.id.button)
        itemEdt = findViewById(R.id.AddItem)
        val itemList = mutableListOf<String>()
        database = FirebaseDatabase.getInstance().reference
        userId = FirebaseAuth.getInstance().currentUser!!.uid

        // on below line we are adding items to our list
//        itemList.add("Corn on the Cob")
//        itemList.add("Baby Shanghai Bok Choy")
//        itemList.add("Sweet Potato ")
//        itemList.add("Shiitake Mushrooms ")
//        itemList.add("Green Onions ")
//        itemList.add("Super Sweet Fresh Corn ")

        // on below line we are initializing adapter for our list view.
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@GroceryListActivity,
            android.R.layout.simple_list_item_1,
            itemList as List<String?>
        )

        // on below line we are setting adapter for our list view.
        itemView.adapter = adapter

        //sends to firebase console
        database = FirebaseDatabase.getInstance().reference
            .child("users")
            .child(userId)
            .child("grocery-list")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                itemList.clear()
                for (ds in dataSnapshot.children) {
                    val item = ds.getValue(String::class.java)
                    itemList.add(item!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
            }
        })

        // on below line we are adding click listener for our button.
        addBtn.setOnClickListener {

            // on below line we are getting text from edit text
            val item = itemEdt.text.toString()
            database.push().setValue(item)
            itemEdt.text.clear()
            Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show()

            // on below line we are checking if item is not empty
            if (item.isNotEmpty()) {
                // on below line we are adding item to our list.
                itemList.add(item)

                // on below line we are notifying adapter
                // that data in list is updated to update our list view.
                adapter.notifyDataSetChanged()
            }
        }
//        end of "add" button

    }
}
