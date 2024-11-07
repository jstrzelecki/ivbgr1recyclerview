package com.example.ivgr1recyclerview

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         val sweetsList = mutableListOf(
             Sweets("miętus",  2.00),
             Sweets("krówka", 12.55),
             Sweets("ekler", 6.50),
             Sweets("beza", 0.95),
             Sweets("lody", 22.15),
         )

        val recyclerView = findViewById<RecyclerView>(R.id.sweets_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(sweetsList) { sweet ->
            Toast.makeText(
                this,
                "Wybrałeś: ${sweet.name} za ${sweet.price}",
                Toast.LENGTH_LONG
            ).show()
        }
        recyclerView.adapter = adapter

        val saveButton: Button =findViewById(R.id.save_list_to_json_button)
        saveButton.setOnClickListener {
            try{
                SweetsJsonManger.saveSweetsListToJson(this, sweetsList)
                Toast.makeText(this, "Zapisano dane", Toast.LENGTH_LONG).show()
            }catch(ex: Exception){
                Log.e("save", "Cos poszło nie tak $ex")
            }
        }

        val loadButton: Button = findViewById(R.id.load_list_to_json_button)
        loadButton.setOnClickListener {
            try{
                val loadedSweetsList = SweetsJsonManger.loadSweetsListFromJson(this)
                Toast.makeText(this, "Wczytano ${loadedSweetsList.size} elementów", Toast.LENGTH_LONG).show()
                sweetsList.clear()
                sweetsList.addAll(loadedSweetsList)
                adapter.notifyDataSetChanged()



            }catch(ex: Exception){
                Log.e("load", "Coś nie tak $ex")
            }
        }

    }
}