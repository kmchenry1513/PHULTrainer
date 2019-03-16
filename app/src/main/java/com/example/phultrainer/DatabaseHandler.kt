package com.example.phultrainer

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                ID + " INTEGER PRIMARY KEY," + DAYNUM + " INTEGER," +
                NAME + " TEXT," + SETREP + " TEXT," +
                WEIGHT + " FLOAT);"

        val CREATE_TABLE2 = "CREATE TABLE $TABLE_NAME2 (" +
                ID + " INTEGER PRIMARY KEY," + DAYNUM + " INTEGER," +
                NAME + " TEXT," + SETREP + " TEXT," +
                WEIGHT + " FLOAT);"

        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        val DROP_TABLE2 = "DROP TABLE IF EXISTS " + TABLE_NAME2
        db.execSQL(DROP_TABLE)
        db.execSQL(DROP_TABLE2)
        onCreate(db)
    }

    //TODO make another add fun for previous workouts and have finish workout in workoutacivity call it
    fun addExercise(exercise: Exercise): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DAYNUM, exercise.dayNum)
        values.put(NAME, exercise.name)
        values.put(SETREP, exercise.setRep)
        values.put(WEIGHT, exercise.weight)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun getSize() : Long{
        val db = readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, "Exercises")
        db.close()
        return count
    }

    fun getExercise(_id: Int): Exercise {
        val exercise = Exercise()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor != null) {
            cursor.moveToFirst()
            while (!(cursor.isAfterLast)) {
                exercise.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                exercise.dayNum = cursor.getInt(cursor.getColumnIndex(DAYNUM))
                exercise.name = cursor.getString(cursor.getColumnIndex(NAME))
                exercise.setRep = cursor.getString(cursor.getColumnIndex(SETREP))
                exercise.weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT))

                cursor.moveToNext()
            }
        }
        cursor.close()


        return exercise
    }

    val exercises: List<Exercise>
        get() {
            val exerciseList = ArrayList<Exercise>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (!(cursor.isAfterLast)) {
                    val exercise = Exercise()
                    exercise.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    exercise.dayNum = cursor.getInt(cursor.getColumnIndex(DAYNUM))
                    exercise.name = cursor.getString(cursor.getColumnIndex(NAME))
                    exercise.setRep = cursor.getString(cursor.getColumnIndex(SETREP))
                    exercise.weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT))
                    exerciseList.add(exercise)

                    cursor.moveToNext()
                }
            }
            cursor.close()
            return exerciseList
        }

    fun updateExercise(exercise: Exercise): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DAYNUM, exercise.dayNum)
        values.put(NAME, exercise.name)
        values.put(SETREP, exercise.setRep)
        values.put(WEIGHT, exercise.weight)
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(exercise.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteExercise(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, "id="+_id, null)
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "MyExercises"
        private val TABLE_NAME = "Exercises"
        private val ID = "Id"
            private val DAYNUM = "DayNum"
        private val NAME = "Name"
        private val SETREP = "SetRep"
        private val WEIGHT = "Weight"

        private val TABLE_NAME2 = "Previous"
    }
}  
