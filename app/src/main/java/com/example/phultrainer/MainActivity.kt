package com.example.phultrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startWorkout(view: View){
        val workoutActivity = Intent(this, WorkoutActivity::class.java)
        when(view.id){
            day1Workout.id -> workoutActivity.putExtra("dayNumber", 1)
            day2Workout.id -> workoutActivity.putExtra("dayNumber", 2)
            day3Workout.id -> workoutActivity.putExtra("dayNumber", 3)
            day4Workout.id -> workoutActivity.putExtra("dayNumber", 4)
        }

        startActivity(workoutActivity)
    }

    fun viewPrevious(view: View){
        val previousActivity = Intent(this, PreviousActivity::class.java)
        startActivity(previousActivity)
    }
}
