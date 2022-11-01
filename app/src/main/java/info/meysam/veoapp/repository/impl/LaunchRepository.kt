package info.meysam.veoapp.repository.impl


import androidx.annotation.WorkerThread
import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.veoapp.data.local.LaunchDetailDao
import info.meysam.veoapp.data.model.ErrorResponse
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.data.remote.LaunchService

class LaunchRepository(
    private val launchService: LaunchService,
    private val launchDetailDao: LaunchDetailDao
) {

    suspend fun getAllLaunches(): NetworkResponse<ArrayList<Launch>, ErrorResponse> {
        return launchService.getLaunches()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(launch: Launch) {
        launchDetailDao.insertLaunchDetail(launch)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getDetail(id: String):Launch? {
        return  launchDetailDao.loadLaunchDetailById(id)
    }
}



