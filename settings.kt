package com.example.traderjoes20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.jar.Attributes.Name

class settings : AppCompatActivity() {
    var tagsList: ArrayList<String> = ArrayList()
    var name: ArrayList<String> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_settings)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAssest())
            val tagArray = obj.getJSONArray("tags")
            for (i in 0 until tagArray.length()) {
                val tagDetails = tagArray.getJSONObject(i)
                //tag or name
                tagsList.add(tagDetails.getString("Name"))
                val tags = tagDetails.getJSONObject("tags")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val customAdapter = CustomAdapter(this@settings, tagsList)
        recyclerView.adapter = customAdapter
    }
    private fun loadJSONFromAssest(): String {
            val json: String?
            try {
                val inputStream = assets.open("recipes.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
               // val charset: Charset = CharSets.UTF_8 charset.utf gave me errors idk why
                val charset: Charset = Charsets.UTF_8
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, charset)

            }
            catch (ex: IOException) {
                ex.printStackTrace()
                return ""

            }
            return json


        }
    }
   //public arrayAdapter(this, R.layout.itemListView, int textViewResourceId)
  // ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_);
