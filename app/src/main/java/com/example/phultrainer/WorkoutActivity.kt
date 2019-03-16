package com.example.phultrainer

import android.content.Intent
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
    var dbHandler: DatabaseHandler? = null
    private var timer: CountDownTimer? = null
    var exerciseList:ArrayList<Exercise> = ArrayList()
    var dayExerciseList:ArrayList<Exercise> = ArrayList()
    var day:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHandler = DatabaseHandler(this)

        day = intent.getIntExtra("dayNumber", 0)

        when(day){
            //These are all currently set to one because tags need to be added for the textviews on the other layouts
            1 -> setContentView(R.layout.day1_activity)
            2 -> setContentView(R.layout.day1_activity)
            3 -> setContentView(R.layout.day1_activity)
            4 -> setContentView(R.layout.day1_activity)
        }

        initDayList()
        addWeights()

    }

    private fun initDayList() {
        dayExerciseList.clear()

        exerciseList = dbHandler?.exercises as ArrayList<Exercise>

        exerciseList.forEach{
            if(it.dayNum == day){
                dayExerciseList.add(it)
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
        initDayList()
        addWeights()
    }

    private fun addWeights(){

        val root = findViewById<ViewGroup>(R.id.mainlayout)
        displayWeights(root)
    }

    private fun displayWeights(view: ViewGroup) {

        for (i in 0 until view.childCount - 1) {
            val v = view.getChildAt(i)
 if (v is ViewGroup) {
     //TODO give tags for day view 2,3,4
                val editText = v.findViewWithTag<TextView>("name")
                val editText2 = v.findViewWithTag<TextView>("weight")
                editText.text = dayExerciseList[i].name + "\t(" + dayExerciseList[i].setRep + ")"
                editText2.text = dayExerciseList[i].weight.toString()
                this.displayWeights(v)
            }
        }
    }
}

