package com.example.ivgr1recyclerview

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

object SweetsJsonManger{
    private const val FILE_NAME = "sweets_data.json"

    fun saveSweetsListToJson(context: Context, sweetsList: List<Sweets>){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(sweetsList)
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(jsonString)

    }

    fun loadSweetsListFromJson(context: Context): List<Sweets>{
        val file = File(context.filesDir, FILE_NAME)
        return if (file.exists()){
            val jsonString = file.readText()
            Gson().fromJson(jsonString, Array<Sweets>::class.java).toList()
        } else {
            emptyList()
        }
    }

}