package info.meysam.veoapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FlickerConverters {
  @TypeConverter
  fun fromFlicker(value: Flickr): String {
    val gson = Gson()
    val type = object : TypeToken<Flickr>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toFlicker(value: String): Flickr {
    val gson = Gson()
    val type = object : TypeToken<Flickr>() {}.type
    return gson.fromJson(value, type)
  }
}