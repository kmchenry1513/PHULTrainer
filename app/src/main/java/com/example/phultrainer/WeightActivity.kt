package com.example.phultrainer

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class WeightActivity : AppCompatActivity(){

    private var dayExerciseList:ArrayList<Exercise> = ArrayList()
    private var linearLayout:LinearLayout? = null
    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DatabaseHandler(this)

        setContentView(R.layout.weight_activity)

         linearLayout = findViewById<LinearLayout>(R.id.linlyt)


        dayExerciseList = intent.getParcelableArrayListExtra("exercises")



        for(i in 0 until dayExerciseList.size) {
            val textView = TextView(this)
            textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.setPadding(20, 20, 20, 20)
            textView.text = dayExerciseList[i].name
            linearLayout?.addView(textView)

            val editText = EditText(this)
            editText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            editText.contentDescription = "Exercise Weight"
            editText.setText(dayExerciseList[i].weight.toString())
            editText.tag = "wgtET$i"


            editText.setPadding(20, 20, 20, 20)
            linearLayout?.addView(editText)
        }

    }

    fun setWeights(view: View){
        findWeights()
        finish()
    }

    private fun findWeights(){

        for(i in 0 until dayExerciseList.size) {
            val editText = linearLayout!!.findViewWithTag<EditText>("wgtET$i")
            var txt = editText.text.toString()

            if(txt == ""){
                txt = "0.0"
            }
            dayExerciseList[i].weight = txt.toDouble()
            dbHandler?.updateExercise(dayExerciseList[i])
        }
    }


}

