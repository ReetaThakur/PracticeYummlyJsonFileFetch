package com.example.practiceyummly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private var recipeList= mutableListOf<Receipe>()
    private lateinit var recipeAdapter:RecipeAdapter

    private val runnable = Runnable {
        readJsonFile()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recipeAdapter= RecipeAdapter(this,recipeList)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=recipeAdapter
        startBackground()
    }

    private fun startBackground() {
        val thread = Thread(runnable)
        thread.start()
    }

    private fun readJsonFile() {
        try {
           var inputStream:InputStream=this.assets.open("recipe.json")
            var data=inputStream.read()
            var builder:StringBuilder= StringBuilder()
            while(data!=-1){
                val ch = data.toChar()
                builder.append(ch)
                data = inputStream.read()
            }
            buildPojoFromJson(builder.toString())
            
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun buildPojoFromJson(json:String) {
        val type = object : TypeToken<responseModel?>() {}.type
        val responseModel: responseModel = Gson().fromJson(json, type)
        recipeList= responseModel.Receipe as MutableList<Receipe>
        updateUi()
    }

    private fun updateUi() {
        runOnUiThread {
            recipeAdapter.updateData(recipeList)
        }
    }


}