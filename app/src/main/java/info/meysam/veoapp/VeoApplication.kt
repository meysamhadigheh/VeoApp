package info.meysam.veoapp

import android.app.Application
import info.meysam.veoapp.data.remote.LaunchService
import info.meysam.veoapp.repository.impl.LaunchRepository

class VeoApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val service by lazy { LaunchService.instance }
    val repository by lazy { LaunchRepository(service) }
}