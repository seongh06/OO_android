package ggum.oo.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class ContentData(private val context: Context) {

    fun loadContentItems(fileName: String): List<ContentItem> {
        val jsonString = readJsonFile(fileName)
        return parseJsonToContentItems(jsonString)
    }

    private fun readJsonFile(fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.use { it.readText() }
    }

    private fun parseJsonToContentItems(jsonString: String): List<ContentItem> {
        val listType = object : TypeToken<List<ContentItem>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
