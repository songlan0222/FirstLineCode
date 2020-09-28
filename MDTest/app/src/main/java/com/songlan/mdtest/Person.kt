package com.songlan.mdtest

import android.os.Parcel
import android.os.Parcelable

class Person : Parcelable {
    var name = ""
    var age = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            val person = Person()
            person.name = parcel.readString() ?: ""
            person.age = parcel.readInt()
            return person
        }

        override fun newArray(percel: Int): Array<Person?> {
            return arrayOfNulls(percel)
        }

    }
}