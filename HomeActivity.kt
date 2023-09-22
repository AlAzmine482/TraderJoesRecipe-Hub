package com.example.traderjoes20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class HomeActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //on page
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //NEED TO SET UP THE RECYCLER VIEW


        //each button functionality
        val buttonToPantry = findViewById<Button>(R.id.btnMyPantry)
        buttonToPantry.setOnClickListener{
            val intent = Intent(this, MyPantryActivity::class.java)
            startActivity(intent)
        }

        val buttonToRecipeHub = findViewById<Button>(R.id.btnRecipeHub)
        buttonToRecipeHub.setOnClickListener{
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }

        val buttonToShop = findViewById<Button>(R.id.btnShop)
        buttonToShop.setOnClickListener{
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }
}