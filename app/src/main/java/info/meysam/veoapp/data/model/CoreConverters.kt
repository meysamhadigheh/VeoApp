package info.meysam.veoapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoreConverters {
  @TypeConverter
  fun fromCoreList(value: List<Core>): String {
    val gson = Gson()
    val type = object : TypeToken<List<Core>>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toCoreList(value: String): List<Core> {
    val gson = Gson()
    val type = object : TypeToken<List<Core>>() {}.type
    return gson.fromJson(value, type)
  }
}