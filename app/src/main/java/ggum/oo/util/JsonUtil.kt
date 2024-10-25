package ggum.oo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ggum.oo.data.ContentItem
import java.io.BufferedReader

object JsonUtil {
    fun loadJsonData(context: Context, fileName: String): List<ContentItem> {
        val jsonString = context.assets.open(fileName).bufferedReader().use(BufferedReader::readText)
        val listType = object : TypeToken<List<ContentItem>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
