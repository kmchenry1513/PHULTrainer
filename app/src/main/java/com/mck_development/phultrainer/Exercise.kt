package com.mck_development.phultrainer

import android.os.Parcel
import android.os.Parcelable

class Exercise() : Parcelable{
    var id: Int = 0
    var dayNum:Int = 0
    var name:String = ""
    var setRep:String = ""
    var weight:Double = 0.0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        dayNum = parcel.readInt()
        name = parcel.readString()
        setRep = parcel.readString()
        weight = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(dayNum)
        parcel.writeString(name)
        parcel.writeString(setRep)
        parcel.writeDouble(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}