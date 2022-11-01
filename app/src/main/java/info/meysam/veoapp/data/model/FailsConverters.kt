package info.meysam.veoapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FailsConverters {
  @TypeConverter
  fun fromFailList(value: List<Fail>): String {
    val gson = Gson()
    val type = object : TypeToken<List<Fail>>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toFailList(value: String): List<Fail> {
    val gson = Gson()
    val type = object : TypeToken<List<Fail>>() {}.type
    return gson.fromJson(value, type)
  }
}