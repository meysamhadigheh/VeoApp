package info.meysam.veoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.meysam.veoapp.data.model.Launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Launch::class), version = 1, exportSchema = false)
public abstract class LaunchDetailRoomDatabase : RoomDatabase() {

   abstract fun launchDetailDao(): LaunchDetailDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: LaunchDetailRoomDatabase? = null

        fun getDatabase(context: Context): LaunchDetailRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        LaunchDetailRoomDatabase::class.java,
                        "launch_detail_database"
                    ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
   }
}
