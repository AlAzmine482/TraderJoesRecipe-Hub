import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.traderjoes20.R
//import com.example.traderjoes20.RecipeJson
//package com.example.traderjoes20

private val Any.recipes: Any get() {}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myrecipies)

        val recipeJson = RecipeJson(this)
        val recipe = recipeJson.recipes.first()

        val ingredientsTextView = findViewById<TextView>(R.id.ingredient_list)
        ingredientsTextView.text = recipe.ingredients.joinToString("\n")

        val tagsTextView = findViewById<TextView>(R.id.recipe_name)
        tagsTextView.text = recipe.tags.joinToString(", ") { it.name }
    }

    private fun RecipeJson(mainActivity: MainActivity): Any {

    }
}
