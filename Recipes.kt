package com.example.traderjoes20

import org.json.JSONArray

data class Recipes(
    var img: String,
    //var tagId: Int,
    //var name: String,
    var ingredients: MutableList<String>,
    var serves: String?,
    var tagIds: JSONArray?,
    var title: String?,
    var directions: String?,
    var cookingTime: String?,
    var prepTime: String?,
    var id: String?,
)