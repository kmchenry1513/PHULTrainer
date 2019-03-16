package com.example.phultrainer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.previous_activity.*

class PreviousActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var exerciseList:ArrayList<Exercise> = ArrayList()
    var list:ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previous_activity)

        dbHandler = DatabaseHandler(this)

        initList()
        displayList()
    }

    private fun initList() {
        exerciseList.clear()
        list.clear()

        exerciseList = dbHandler?.previous as ArrayList<Exercise>
        for (i in 0 until exerciseList.size){
            var str = exerciseList[i].name + ", Sets/Reps: "  + exerciseList[i].setRep + ", Weight: " + exerciseList[i].weight.toString() + " Lbs."
            list.add(str)
        }

        }

    private fun displayList(){
        var listView = lv
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
    }

    }
