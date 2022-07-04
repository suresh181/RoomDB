package com.my.roomdb

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user")
data class Users(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name =  "userId")
                 var userId: Int? = null,
                 @ColumnInfo(name =  "userName")
                 val userName: String,
                 @ColumnInfo(name =  "location")
                 var location: String,
                 @ColumnInfo(name =  "email")
                 val email: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(userId)
        parcel.writeString(userName)
        parcel.writeString(location)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Users> {
        override fun createFromParcel(parcel: Parcel): Users {
            return Users(parcel)
        }

        override fun newArray(size: Int): Array<Users?> {
            return arrayOfNulls(size)
        }
    }
}