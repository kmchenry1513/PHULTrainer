package com.mck_development.phultrainer

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.previous_activity.*
import java.util.*
import kotlin.collections.LinkedHashMap


class PreviousActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var exerciseList:ArrayList<Exercise> = ArrayList()
    var dateList:ArrayList<String> = ArrayList()
    var list:ArrayList<String> = ArrayList()

    var map : LinkedHashMap<String,String> = LinkedHashMap()


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
        dateList.clear()

        exerciseList = dbHandler?.previous as ArrayList<Exercise>
        dateList = dbHandler?.date as ArrayList<String>

        for (i in 0 until exerciseList.size){
            var str = exerciseList[i].name + "\nSets/Reps: "  + exerciseList[i].setRep + "\nWeight: " + exerciseList[i].weight.toString() + " Lbs.\n\n"

            when(i){
                0 -> map[dateList[i] + " (" + exerciseList[i].dayNum + ")"] = str
                else ->{
                    if(map.containsKey(dateList[i] + " (" + exerciseList[i].dayNum + ")")){
                        map[dateList[i] + " (" + exerciseList[i].dayNum + ")"] = map[dateList[i] + " (" + exerciseList[i].dayNum + ")"] + str
                    }else {
                        map[dateList[i] + " (" + exerciseList[i].dayNum + ")"] = str
                    }
                }
            }
        }

        map.forEach { (key) ->  list.add(key)}
        }

    private fun displayList(){
        list.sort()
        var listView = lv
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        listView.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String
                val workout = map[itemValue]
                showDialog(itemValue, workout)
            }

        }
    }

    private fun showDialog(itemValue: String, workout: String?){
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(this)

        builder.setTitle(itemValue)
        builder.setMessage(workout)
        dialog = builder.create()

        dialog.show()
    }
}


