package info.meysam.veoapp.ui.fragments.launchdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel

class LaunchDetailsViewModel : ViewModel() {

    fun openYoutube(context: Context, id: String) {
        if (id.contains("https://")) {
            val uri: Uri = Uri.parse(id) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(context, intent, null)
        }
    }
}