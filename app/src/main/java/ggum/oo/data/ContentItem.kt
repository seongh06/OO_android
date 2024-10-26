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
        id = parcel.readInt(),
        category = parcel.readByte() != 0.toByte(),
        area = parcel.readByte() != 0.toByte(),
        title = parcel.readString() ?: "",
        body = parcel.readString() ?: "",
        image = parcel.readValue(Int::class.java.classLoader) as? Int,
        date = parcel.readString() ?: "",
        commentCount = parcel.readInt(),
        isFavorite = parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeByte(if (category) 1 else 0)
        parcel.writeByte(if (area) 1 else 0)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeValue(image)
        parcel.writeString(date)
        parcel.writeInt(commentCount)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ContentItem> {
        override fun createFromParcel(parcel: Parcel): ContentItem = ContentItem(parcel)
        override fun newArray(size: Int): Array<ContentItem?> = arrayOfNulls(size)
    }

    // 게시글 추가 메서드
    fun submitPost(title: String, body: String, date: String, imageResId: Int? = null, area: Boolean) {
        val newContentItem = ContentItem(
            id = ContentList.items.size + 1, // 새로운 ID 생성
            category = false, // 기본값 설정
            area = area, // 사용자 입력으로 대체
            title = title,
            body = body,
            image = imageResId,
            date = date,
            commentCount = 0, // 초기 댓글 수
            isFavorite = false // 기본값 설정
        )
        // 새로운 항목 추가
        ContentList.addItem(newContentItem)
    }
}
