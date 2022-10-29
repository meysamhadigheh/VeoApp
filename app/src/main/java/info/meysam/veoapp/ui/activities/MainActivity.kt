package info.meysam.veoapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.meysam.veoapp.R
import info.meysam.veoapp.ui.fragments.launches.LaunchesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LaunchesFragment.newInstance())
                .commitNow()
        }
    }
}