package com.example.phultrainer

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.day1_activity.*
import android.view.ViewGroup
import android.widget.TextView


class WorkoutActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    var exerciseList:ArrayList<Exercise> = ArrayList()
    var dayExerciseList:ArrayList<String> = ArrayList()
    var day:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPref.edit()

        editor.clear()
        editor.commit()

        day = intent.getIntExtra("dayNumber", 0)

        when(day){
            1 -> setContentView(R.layout.day1_activity)
            2 -> setContentView(R.layout.day2_activity)
            3 -> setContentView(R.layout.day3_activity)
            4 -> setContentView(R.layout.day4_activity)
        }

        setExerciseList()
        addWeights()
    }

    private fun setExerciseList(){
        dayExerciseList.clear()

        exerciseList.forEach{ exercise ->
            if (exercise.dayNum.toInt() == day){
                dayExerciseList.add(exercise.name)
            }
        }

    }

    fun addRep(view: View){
        val btn = findViewById<Button>(view.id)
        var count = btn.text.toString().toInt()
        count += 1
        btn.text = count.toString()
    }

    fun startTimer(view: View){
        val timerTV = timerTV
        startTimerBtn.isClickable = false

        timer = object: CountDownTimer(30100, 1000) {
            override fun onFinish() {
                timerTV.text = "30s"
                startTimerBtn.isClickable = true
            }

            override fun onTick(millisUntilFinished: Long) {
                timerTV.text = (millisUntilFinished / 1000).toString() + "s"
            }
        }.start()

    }

    fun setWeight(view: View){
        val weightActivity = Intent(this, WeightActivity::class.java)
        weightActivity.putExtra("exercises", dayExerciseList)
        startActivityForResult(weightActivity, 0)
    }

    fun finishWorkout(view: View){
        //TODO add workout to DB
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        addWeights()
    }

    private fun addWeights(){

        val root = findViewById<ViewGroup>(R.id.mainlayout)
        displayWeights(root)
    }

    private fun displayWeights(view: ViewGroup) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        for (i in 0 until view.childCount - 1) {
            val v = view.getChildAt(i)
 if (v is ViewGroup) {
                val editText = v.findViewWithTag<TextView>("weight")
                editText.text = sharedPref.getString("weight$i", "0") + " Lbs."
                this.displayWeights(v)
            }
        }
    }
}

