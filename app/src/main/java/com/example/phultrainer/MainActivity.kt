package com.example.phultrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDB()
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
//        val previousActivity = Intent(this, PreviousActivity::class.java)
//        startActivity(previousActivity)

    }

    private fun initDB() {

        dbHandler = DatabaseHandler(this)

        val dbSize = dbHandler?.getSize()
        var exercise: Exercise = Exercise()

        if (dbSize != null) {
            if(dbSize < 1){


                exercise.dayNum = 1
                exercise.name = "Barbell Bench Press"
                exercise.setRep = "4x5"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 1
                exercise.name = "Incline Dumbbell Bench Press"
                exercise.setRep = "4x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 1
                exercise.name = "Bent Over Row"
                exercise.setRep = "4x5"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 1
                exercise.name = "Lat Pull Down"
                exercise.setRep = "4x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 1
                exercise.name = "Overhead Press"
                exercise.setRep = "3x8"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 1
                exercise.name = "Barbell Curl"
                exercise.setRep = "3x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)
                exercise.dayNum = 1
                exercise.name = "Skullcrushers"
                exercise.setRep = "3x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 2
                exercise.name = "Squat"
                exercise.setRep = "4x5"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 2
                exercise.name = "Deadlift"
                exercise.setRep = "4x5"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 2
                exercise.name = "Leg Press"
                exercise.setRep = "5x15"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 2
                exercise.name = "Leg Curl"
                exercise.setRep = "4x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 2
                exercise.name = "Calf Exercise (any)"
                exercise.setRep = "4x10"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Incline Barbell Bench Press"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Flat Bench Dumbbell Fly"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Seated Cable Row"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "One Arm Dumbbell Row"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Dumbbell Lateral Raise"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Seated Incline Dumbbell Curl"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 3
                exercise.name = "Cable Tricep Extension"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Front Squat"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Barbell Lunge"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Leg Extension"
                exercise.setRep = "4x15"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Leg Curl"
                exercise.setRep = "4x15"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Seated Calf Raise"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)

                exercise.dayNum = 4
                exercise.name = "Calf Press"
                exercise.setRep = "4x12"
                exercise.weight = 0.0

                dbHandler?.addExercise(exercise)
            }
        }



    }
}
