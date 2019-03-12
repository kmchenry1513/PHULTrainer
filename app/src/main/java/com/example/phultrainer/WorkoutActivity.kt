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

        exerciseList = initExerciseObjs()
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

    private fun initExerciseObjs():ArrayList<Exercise> {

        exerciseList.add((Exercise("1", "Barbell Bench Press", "4x5")))
        exerciseList.add((Exercise("1", "Incline Dumbbell Bench Press", "4x10")))
        exerciseList.add((Exercise("1", "Bent Over Row", "4x5")))
        exerciseList.add((Exercise("1", "Lat Pull Down", "4x10")))
        exerciseList.add((Exercise("1", "Overhead Press", "3x8")))
        exerciseList.add((Exercise("1", "Barbell Curl", "3x10")))
        exerciseList.add((Exercise("1", "Skullcrushers", "3x10")))

        exerciseList.add((Exercise("2", "Squat", "4x5")))
        exerciseList.add((Exercise("2", "Deadlift", "4x5")))
        exerciseList.add((Exercise("2", "Leg Press", "5x15")))
        exerciseList.add((Exercise("2", "Leg Curl", "4x10")))
        exerciseList.add((Exercise("2", "Calf Exercise", "4x10")))

        exerciseList.add((Exercise("3", "Incline Barbell Bench Press", "4x12")))
        exerciseList.add((Exercise("3", "Flat Bench Dumbbell Fly", "4x12")))
        exerciseList.add((Exercise("3", "Seated Cable Row", "4x12")))
        exerciseList.add((Exercise("3", "One Arm Dumbbell Row", "4x12")))
        exerciseList.add((Exercise("3", "Dumbbell Lateral Raise", "4x12")))
        exerciseList.add((Exercise("3", "Seated Incline Dumbbell Curl", "4x12")))
        exerciseList.add((Exercise("3", "Cable Tricep Extension", "4x12")))

        exerciseList.add((Exercise("4", "Front Squat", "4x12")))
        exerciseList.add((Exercise("4", "Barbell Lunge", "4x12")))
        exerciseList.add((Exercise("4", "Leg Extension", "4x15")))
        exerciseList.add((Exercise("4", "Leg Curl", "4x15")))
        exerciseList.add((Exercise("4", "Seated Calf Raise", "4x12")))
        exerciseList.add((Exercise("4", "Calf Press", "4x12")))

        return exerciseList
    }
}

class Exercise (val dayNum:String, val name:String, val setRep:String)