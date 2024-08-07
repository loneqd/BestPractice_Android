package com.lqd.androidpractice.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.lqd.androidpractice.R
import com.lqd.androidpractice.view.glide.HorizontalGalleryFragment

class GlideExampleActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_glide_example)
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH)
        if (PERMISSIONS_REQUEST.any {
                ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }) {
            requestStoragePermission()
        } else {
            replaceFragment()
        }
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this, PERMISSIONS_REQUEST, REQUEST_READ_STORAGE
        )
    }

    private fun replaceFragment() {
        val fragment: Fragment = HorizontalGalleryFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        private const val REQUEST_READ_STORAGE = 0
        private val PERMISSIONS_REQUEST =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
            } else {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
    }
}