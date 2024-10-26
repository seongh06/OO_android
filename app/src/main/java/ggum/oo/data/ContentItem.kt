package ggum.oo.data

import android.os.Parcel
import android.os.Parcelable

data class ContentItem(
    val id: Int,
    val category: Boolean,
    val area: Boolean,
    val title: String,
    val body: String,
    val image: Int?,
    val date: String,
    val commentCount: Int,
    val isFavorite: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte(), // category
        parcel.readByte() != 0.toByte(), // area
        parcel.readString() ?: "", // title
        parcel.readString() ?: "", // body
        parcel.readValue(Int::class.java.classLoader) as? Int, // image
        parcel.readString() ?: "", // date
        parcel.readInt(), // commentCount
        parcel.readByte() != 0.toByte() // isFavorite
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeByte(if (category) 1 else 0) // category
        parcel.writeByte(if (area) 1 else 0) // area
        parcel.writeString(title) // title
        parcel.writeString(body) // body
        parcel.writeValue(image) // image
        parcel.writeString(date) // date
        parcel.writeInt(commentCount) // commentCount
        parcel.writeByte(if (isFavorite) 1 else 0) // isFavorite
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContentItem> {

        override fun createFromParcel(parcel: Parcel): ContentItem {
            return ContentItem(parcel)
        }

        override fun newArray(size: Int): Array<ContentItem?> {
            return arrayOfNulls(size)
        }
    }
}
