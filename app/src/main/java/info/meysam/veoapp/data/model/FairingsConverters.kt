package info.meysam.veoapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FairingsConverters {
  @TypeConverter
  fun fromCapsuleList(value: Fairing): String {
    val gson = Gson()
    val type = object : TypeToken<Fairing>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toCapsuleList(value: String): Fairing {
    val gson = Gson()
    val type = object : TypeToken<Fairing>() {}.type
    return gson.fromJson(value, type)
  }
}