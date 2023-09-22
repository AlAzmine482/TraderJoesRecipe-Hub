package com.example.traderjoes20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyPantryActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pantry)
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        database = FirebaseDatabase.getInstance().reference

        //each "item" in the MyPantry View
        val pantryList = findViewById<ListView>(R.id.pantryList)
        val addItem = findViewById<EditText>(R.id.AddItem)
        val addButton = findViewById<Button>(R.id.button)

        val items = mutableListOf<String>()
        val adapter = PantryListAdapter(this, items)
        pantryList.adapter = adapter

        //sends to firebase console
        database = FirebaseDatabase.getInstance().reference
            .child("users")
            .child(userId)
            .child("pantry")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                items.clear()
                for (ds in dataSnapshot.children) {
                    val item = ds.getValue(String::class.java)
                    items.add(item!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
            }
        })

        addButton.setOnClickListener {
            val item = addItem.text.toString()
            database.push().setValue(item)
            addItem.text.clear()
            Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show()
        }

        val btnGroceryList = findViewById<Button>(R.id.btnGroceryList)
        btnGroceryList.setOnClickListener {
            startActivity(Intent(this, GroceryListActivity::class.java))
        }
    }
}