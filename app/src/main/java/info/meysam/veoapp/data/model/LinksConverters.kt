package info.meysam.veoapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LinksConverters {
  @TypeConverter
  fun fromLinkList(value: Links): String {
    val gson = Gson()
    val type = object : TypeToken<Links>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toLinkList(value: String): Links {
    val gson = Gson()
    val type = object : TypeToken<Links>() {}.type
    return gson.fromJson(value, type)
  }
}