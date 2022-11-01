package info.meysam.veoapp.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.veoapp.data.model.ErrorResponse
import info.meysam.veoapp.data.model.Launch
import info.meysam.veoapp.data.remote.LaunchService

class LaunchRepository(
    private val launchService: LaunchService,
) {

    suspend fun getAllLaunches(): NetworkResponse<List<Launch>, ErrorResponse> {
        return launchService.getLaunches()
    }

}



