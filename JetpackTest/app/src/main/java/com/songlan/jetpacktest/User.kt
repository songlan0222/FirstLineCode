package com.songlan.jetpacktest

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var firstName: String, var lastName: String, var age: Int) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeLong(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            val id = parcel.readLong()
            val firstName = parcel.readString() ?: ""
            val lastName = parcel.readString() ?: ""
            val age = parcel.readInt()
            val user = User(firstName, lastName, age)
            user.id = id
            return user
        }

        override fun newArray(p0: Int): Array<User?> {
            return arrayOfNulls<User>(p0)
        }

    }
}
