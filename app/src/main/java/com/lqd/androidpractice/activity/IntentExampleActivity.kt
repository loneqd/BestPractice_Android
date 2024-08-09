package com.lqd.androidpractice.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.lifecycle.ProcessLifecycleOwner

import com.lqd.androidpractice.R
import com.lqd.androidpractice.databinding.ActivityIntentExampleBinding

class IntentExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityIntentExampleBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)
        }

        binding.actionInsertBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT).apply {
                data = Events.CONTENT_URI
                putExtra(Events.TITLE, "Learn Android")
                putExtra(Events.EVENT_LOCATION, "Home")
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + 60 * 60 * 1000)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 0)
            }
        }

        binding.imageBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, "file:///sdcard/temp.jpg")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 1)
            }
        }


        binding.contactBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 2)
            }
        }

        binding.emailBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL, "xxx@yyy.com")
                putExtra(Intent.EXTRA_SUBJECT, "This is a subject")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 3)
            }
        }

        binding.cameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 4)
            }
        }






    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}