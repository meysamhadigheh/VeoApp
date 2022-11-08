package info.meysam.veoapp.data.local

import androidx.room.*
import info.meysam.veoapp.data.model.Launch

@Dao
interface LaunchDetailDao {
    @Query("SELECT * FROM launch_detail_table")
    suspend fun getAll(): List<Launch>

    @Query("SELECT * FROM launch_detail_table WHERE id =:id")
    suspend fun loadLaunchDetailById(id: Int): Launch?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLaunchDetail(vararg launch: Launch)

    @Delete
    fun delete(launch: Launch)
}